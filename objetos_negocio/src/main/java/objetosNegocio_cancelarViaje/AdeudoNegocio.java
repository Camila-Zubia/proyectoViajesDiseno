/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_cancelarViaje;

import adaptadores.adaptadorAdeudo;
import dto.AdeudoDTO;
import dto.ConductorDTO;
import interfaces_cancelarViaje.IAdeudoNegocio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.impl.AdeudoDAO;
import org.base_datos_viajes.dao.interfaces.IAdeudoDAO;
import org.base_datos_viajes.exception.DatabaseException;
import org.base_datos_viajes.model.Adeudo;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 * Implementación de lógica de negocio para gestión de adeudos
 *
 * @author Camila Zubia 00000244825
 */
public class AdeudoNegocio implements IAdeudoNegocio {

    private final IAdeudoDAO adeudoDAO;

    public AdeudoNegocio() {
        this.adeudoDAO = new AdeudoDAO();
    }

    @Override
    public List<AdeudoDTO> obtenerAdeudos(String idConductor) {
        try {
            // Validar que hay un conductor en sesión
            ConductorDTO conductor = SesionUsuario.obtenerConductor();
            if (conductor == null) {
                throw new IllegalStateException("No hay un conductor en sesión activa");
            }

            // Obtener ObjectId del conductor
            ObjectId conductorObjectId = new ObjectId(conductor.getId());

            // Consultar adeudos desde la base de datos
            List<Adeudo> adeudosEntidad = adeudoDAO.obtenerAdeudosPorConductor(conductorObjectId);

            // Convertir entidades a DTOs
            return adeudosEntidad.stream()
                .map(adaptadorAdeudo::toDTO)
                .collect(Collectors.toList());

        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al obtener adeudos: " + e.getMessage(), e);
        }
    }

    @Override
    public void registrarAdeudo(AdeudoDTO adeudo) {
        try {
            // Validar parámetro
            if (adeudo == null) {
                throw new IllegalArgumentException("El adeudo no puede ser nulo");
            }

            // Validar que hay un conductor en sesión
            ConductorDTO conductor = SesionUsuario.obtenerConductor();
            if (conductor == null) {
                throw new IllegalStateException("No hay un conductor en sesión activa");
            }

            // Obtener ObjectId del conductor
            ObjectId conductorObjectId = new ObjectId(conductor.getId());

            // Convertir DTO a entidad
            Adeudo adeudoEntidad = adaptadorAdeudo.toEntity(adeudo, conductorObjectId);

            // Guardar en base de datos
            Adeudo adeudoGuardado = adeudoDAO.save(adeudoEntidad);

            // Actualizar el DTO con el ID generado
            adeudo.setId(adeudoGuardado.getId().toHexString());

        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al registrar adeudo: " + e.getMessage(), e);
        }
    }

    @Override
    public void marcarComoPagado(String idAdeudo) {
        try {
            // Validar parámetro
            if (idAdeudo == null || idAdeudo.isEmpty()) {
                throw new IllegalArgumentException("El ID del adeudo no puede ser nulo o vacío");
            }

            // Validar que hay un conductor en sesión
            ConductorDTO conductor = SesionUsuario.obtenerConductor();
            if (conductor == null) {
                throw new IllegalStateException("No hay un conductor en sesión activa");
            }

            // Convertir String a ObjectId
            ObjectId adeudoObjectId = new ObjectId(idAdeudo);

            // Verificar que el adeudo existe
            Optional<Adeudo> optionalAdeudo = adeudoDAO.findById(adeudoObjectId);
            if (!optionalAdeudo.isPresent()) {
                throw new IllegalStateException("No se encontró el adeudo con ID: " + idAdeudo);
            }

            // Actualizar solo el campo "pagado" usando actualización parcial
            Map<String, Object> updates = new HashMap<>();
            updates.put("pagado", true);
            adeudoDAO.updatePartial(adeudoObjectId, updates);

        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al marcar adeudo como pagado: " + e.getMessage(), e);
        }
    }
}
