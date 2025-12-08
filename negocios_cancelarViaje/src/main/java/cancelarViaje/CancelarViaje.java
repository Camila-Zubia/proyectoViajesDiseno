/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cancelarViaje;

import dto.ViajeDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class CancelarViaje implements ICancelarViaje {

    private final ControlCancelaciones control;

    public CancelarViaje() {
        this.control = new ControlCancelaciones();
    }

    @Override
    public ViajeDTO seleccionarViaje(String idViaje) {
        return control.seleccionarViaje(idViaje);
    }

    @Override
    public boolean cancelarViaje(String idViaje) {
        return control.cancelarViaje(idViaje);
    }

    @Override
    public ViajeDTO obtenerViajeSeleccionado() {
        return control.obtenerViajeSeleccionado();
    }

    @Override
    public int obtenerAdeudoPendiente(String idViaje) {
        return control.obtenerAdeudoPendiente(idViaje);
    }

    @Override
    public ViajeDTO obtenerDetallesViaje(String idViaje) {
        return control.obtenerDetallesViaje(idViaje);
    }
}
