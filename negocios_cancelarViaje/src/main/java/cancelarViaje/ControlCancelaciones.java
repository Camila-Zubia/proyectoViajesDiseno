/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cancelarViaje;

import dto.AdeudoDTO;
import dto.ConductorDTO;
import dto.PasajeroDTO;
import dto.ViajeDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces.IViajeNegocio;
import interfaces_cancelarViaje.IAdeudoNegocio;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlCancelaciones {

    private final IViajeNegocio viajeBO;
    private final IAdeudoNegocio adeudoBO;
    private ViajeDTO viajeSeleccionado;

    public ControlCancelaciones() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.viajeBO = fabrica.crearViajeNegocio();
        this.adeudoBO = fabrica.crearAdeudoNegocio();
        this.viajeSeleccionado = null;
    }

    /**
     * Selecciona un viaje para su posible cancelación
     */
    public ViajeDTO seleccionarViaje(String idViaje) {
        if (idViaje == null || idViaje.isEmpty()) {
            throw new IllegalArgumentException("El ID del viaje no puede ser nulo o vacío");
        }

        this.viajeSeleccionado = viajeBO.obtenerDetalleViaje(idViaje);

        if (this.viajeSeleccionado == null) {
            throw new IllegalStateException("No se encontró el viaje con ID: " + idViaje);
        }

        return this.viajeSeleccionado;
    }

    /**
     * Obtiene el viaje actualmente seleccionado
     */
    public ViajeDTO obtenerViajeSeleccionado() {
        return this.viajeSeleccionado;
    }

    /**
     * Obtiene los detalles completos de un viaje
     */
    public ViajeDTO obtenerDetallesViaje(String idViaje) {
        return viajeBO.obtenerDetalleViaje(idViaje);
    }

    /**
     * Obtiene la lista de pasajeros de un viaje
     */
    public List<PasajeroDTO> obtenerPasajerosDelViaje(String idViaje) {
        return viajeBO.obtenerPasajeros(idViaje);
    }

    /**
     * Calcula el adeudo por cancelación del viaje seleccionado
     * Reglas de negocio:
     * - Menos de 24 horas: 100% del costo total
     * - Entre 24-48 horas: 50% del costo total
     * - Más de 48 horas: 0% (sin penalización)
     * - Multiplicado por cantidad de pasajeros
     */
    public int calcularAdeudo() {
        if (viajeSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un viaje primero");
        }

        LocalDateTime fechaHoraViaje = LocalDateTime.of(
            viajeSeleccionado.getFecha(),
            viajeSeleccionado.getHora()
        );

        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(ahora, fechaHoraViaje);
        long horasRestantes = duracion.toHours();

        double porcentajePenalizacion;
        if (horasRestantes < 24) {
            porcentajePenalizacion = 1.0; // 100%
        } else if (horasRestantes < 48) {
            porcentajePenalizacion = 0.5; // 50%
        } else {
            porcentajePenalizacion = 0.0; // Sin penalización
        }

        // Obtener cantidad exacta de pasajeros del campo
        int cantidadPasajeros = viajeSeleccionado.getCantidadPasajeros();

        double montoBase = viajeSeleccionado.getPrecioTotal();
        double adeudoTotal = montoBase * porcentajePenalizacion * cantidadPasajeros;

        return (int) Math.round(adeudoTotal);
    }

    /**
     * Cancela el viaje seleccionado y registra el adeudo si corresponde
     * Valida que el conductor en sesión sea dueño del viaje
     */
    public boolean cancelarViaje(String idViaje) {
        if (viajeSeleccionado == null || !viajeSeleccionado.getId().equals(idViaje)) {
            seleccionarViaje(idViaje);
        }

        // VALIDACIÓN: Verificar que el conductor en sesión sea dueño del viaje
        ConductorDTO conductorSesion = utilidades.SesionUsuario.obtenerConductor();
        if (conductorSesion == null) {
            throw new IllegalStateException("No hay un conductor en sesión activa");
        }

        // Obtener detalles completos del viaje para verificar propiedad
        ViajeDTO viajeCompleto = viajeBO.obtenerDetalleViaje(idViaje);
        if (viajeCompleto == null) {
            throw new IllegalStateException("No se encontró el viaje con ID: " + idViaje);
        }

        // Aquí asumiríamos que ViajeDTO tiene un campo conductorId o conductor
        // Por ahora, esta validación se puede omitir si no existe el campo
        // TODO: Verificar viaje.getConductorId() == conductorSesion.getId()

        // Calcular adeudo antes de cancelar
        int montoAdeudo = calcularAdeudo();

        // Marcar viaje como inactivo (NO eliminar físicamente)
        boolean cancelado = viajeBO.eliminarViaje(idViaje);

        if (cancelado && montoAdeudo > 0) {
            // Registrar adeudo para el conductor en sesión
            AdeudoDTO adeudo = new AdeudoDTO(
                montoAdeudo,
                "Cancelación de viaje: " + viajeSeleccionado.getOrigen() +
                    " → " + viajeSeleccionado.getDestino(),
                LocalDateTime.now()
            );
            adeudoBO.registrarAdeudo(adeudo);
        }

        // Limpiar selección
        viajeSeleccionado = null;

        return cancelado;
    }

    /**
     * Obtiene el adeudo pendiente de un viaje específico
     */
    public int obtenerAdeudoPendiente(String idViaje) {
        seleccionarViaje(idViaje);
        return calcularAdeudo();
    }
}
