/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_cancelarViaje;

import dto.AdeudoDTO;
import dto.ConductorDTO;
import interfaces_cancelarViaje.IAdeudoNegocio;
import java.util.ArrayList;
import java.util.List;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class AdeudoNegocio implements IAdeudoNegocio {

    public AdeudoNegocio() { }

    @Override
    public List<AdeudoDTO> obtenerAdeudos(String idConductor) {
        ConductorDTO conductor = SesionUsuario.obtenerConductor();

        if (conductor == null) {
            throw new IllegalStateException("No hay un conductor en sesión activa");
        }

        if (conductor.getAdeudos() == null) {
            conductor.setAdeudos(new ArrayList<>());
        }

        return conductor.getAdeudos();
    }

    @Override
    public void registrarAdeudo(AdeudoDTO adeudo) {
        if (adeudo == null) {
            throw new IllegalArgumentException("El adeudo no puede ser nulo");
        }

        ConductorDTO conductor = SesionUsuario.obtenerConductor();

        if (conductor == null) {
            throw new IllegalStateException("No hay un conductor en sesión activa");
        }

        if (conductor.getAdeudos() == null) {
            conductor.setAdeudos(new ArrayList<>());
        }

        // Generar ID simple (en producción usaría ObjectId)
        adeudo.setId(String.valueOf(System.currentTimeMillis()));

        conductor.getAdeudos().add(adeudo);
    }

    @Override
    public void marcarComoPagado(String idAdeudo) {
        ConductorDTO conductor = SesionUsuario.obtenerConductor();

        if (conductor == null || conductor.getAdeudos() == null) {
            throw new IllegalStateException("No hay adeudos disponibles");
        }

        conductor.getAdeudos().stream()
            .filter(a -> a.getId().equals(idAdeudo))
            .findFirst()
            .ifPresent(a -> a.setPagado(true));
    }
}
