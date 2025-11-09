/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeNegocio {
    
    ConductorNegocio conductorBO = new ConductorNegocio();
    private ViajeDTO viaje1;
    private ViajeDTO viaje2;
    
    public ViajeNegocio() {
        viaje1 = new ViajeDTO("Obregón", "Navojoa", new java.util.Date(),
                java.time.LocalTime.of(10, 30), 250.0);
        viaje2 = new ViajeDTO("Obregon", "Esperanza", new java.util.Date(),
                java.time.LocalTime.of(14, 0), 320.0);
        conductorBO.agregarViaje(viaje1);
        conductorBO.agregarViaje(viaje2);
    }
    
    public List<ViajeDTO> obtenerViajes(ConductorDTO conductor) {
        return conductorBO.obtenerViajes();
    }
    
    public void registrarViaje(ViajeDTO viaje){
        if (validarNoExiste(viaje)) {
            conductorBO.obtenerViajes().add(viaje);
        } else {
            throw new IllegalStateException("Ya existe un viaje con los mismos datos.");
        }
    }
    
    public List<ParadaDTO> obtenerParadas(){
        return viaje1.getParadas();
    }
    
    public void registrarParada(ParadaDTO parada){
        viaje2.getParadas().add(parada);
    }
    
    private boolean validarNoExiste(ViajeDTO viaje){
        List<ViajeDTO> viajes = conductorBO.obtenerViajes();
        for (ViajeDTO v : viajes) {
            if (v == viaje) {
                return false;
            }
        }
        return true;
    }
}
