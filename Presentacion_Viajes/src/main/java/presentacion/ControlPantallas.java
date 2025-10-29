/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.util.List;
import registrarViaje.ControlViaje;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlPantallas {
    
    private final menuPrincipal frame;
    private ControlViaje controlViaje = new ControlViaje();

    public ControlPantallas(menuPrincipal frame) {
        this.frame = frame;
    }

    public void mostrarMenuVehiculos() {
        List vehiculos = controlViaje.obtenerVehiculosDisponibles();
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        frame.configurarPanel(menuVehiculos);
    }

    public void mostrarMenuConductor() {
        //List viajes = controlViaje.obtenerViajes();
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this);
        frame.configurarPanel(menuConductor);
    }

    public void mostrarDatosViaje() {
        datosViaje datosViaje = new datosViaje(this);
        frame.configurarPanel(datosViaje);
    }

    public void mostrarDatosParada() {
        List paradas = controlViaje.obtenerParadasTemporales();
        datosParadas datosParadas = new datosParadas(this, paradas);
        frame.configurarPanel(datosParadas);
    }
}
