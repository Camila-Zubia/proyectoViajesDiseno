/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cancelarViaje;

import dto.ViajeDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface ICancelarViaje {

    /**
     * Selecciona un viaje para cancelación
     * @param idViaje ID del viaje a seleccionar
     * @return ViajeDTO con detalles del viaje seleccionado
     */
    public ViajeDTO seleccionarViaje(String idViaje);

    /**
     * Cancela el viaje seleccionado previamente
     * @param idViaje ID del viaje a cancelar
     * @return true si se canceló exitosamente, false en caso contrario
     */
    public boolean cancelarViaje(String idViaje);

    /**
     * Obtiene el viaje actualmente seleccionado
     * @return ViajeDTO del viaje seleccionado
     */
    public ViajeDTO obtenerViajeSeleccionado();

    /**
     * Obtiene el monto del adeudo pendiente por cancelar un viaje
     * @param idViaje ID del viaje
     * @return monto del adeudo en pesos
     */
    public int obtenerAdeudoPendiente(String idViaje);
}
