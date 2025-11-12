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
    
    List<ViajeDTO> viajes = UsuarioNegocio.obtenerConductor().getViajes();
    private ViajeDTO viaje1;
    private ViajeDTO viaje2;
    
    public ViajeNegocio() {
        viaje1 = new ViajeDTO("Obregón", "Navojoa", new java.util.Date(),
                java.time.LocalTime.of(10, 30), 250.0);
        viaje2 = new ViajeDTO("Obregon", "Esperanza", new java.util.Date(),
                java.time.LocalTime.of(14, 0), 320.0);
        viajes.add(viaje1);
        viajes.add(viaje2);
        viaje1.getParadas().add(new ParadaDTO("Tutuli", 50.0));
        viaje1.getParadas().add(new ParadaDTO("ITSON", 30.0));
        viaje2.getParadas().add(new ParadaDTO("Central camiones", 40.0));
    }
    
    public List<ViajeDTO> obtenerViajes(ConductorDTO conductor) {
        return viajes;
    }
    
    public void registrarViaje(ViajeDTO viaje){
        if (validarNoExiste(viaje)) {
            viajes.add(viaje);
        } else {
            throw new IllegalStateException("Ya existe un viaje con los mismos datos.");
        }
    }
    
    public List<ParadaDTO> obtenerParadas(){
        return viaje1.getParadas();
    }
    
    public void registrarParada(ViajeDTO viaje, ParadaDTO parada){
        viaje.getParadas().add(parada);
    }
    
    private boolean validarNoExiste(ViajeDTO viaje){
        for (ViajeDTO v : viajes) {
            if (v == viaje) {
                return false;
            }
        }
        return true;
    }
}
