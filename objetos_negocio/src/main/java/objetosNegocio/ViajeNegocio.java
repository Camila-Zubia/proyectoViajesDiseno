/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ParadaDTO;
import dto.ViajeDTO;
import interfaces.IViajeNegocio;
import java.util.List;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeNegocio implements IViajeNegocio{
    
    public ViajeNegocio() {
        
    }
    
    @Override
    public void registrarViaje(ViajeDTO viaje){
        if (validarNoExiste(SesionUsuario.obtenerConductor().getViajes(), viaje)) {
            SesionUsuario.obtenerConductor().getViajes().add(viaje);
        } else {
            throw new IllegalStateException("Ya existe un viaje con los mismos datos.");
        }
    }
    
    @Override
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje){
        return viaje.getParadas();
    }
    
    @Override
    public void registrarParada(ViajeDTO viaje, ParadaDTO parada){
        viaje.getParadas().add(parada);
    }
    
    private boolean validarNoExiste(List<ViajeDTO> viajes, ViajeDTO viaje){
        for (ViajeDTO v : viajes) {
            if (v == viaje) {
                return false;
            }
        }
        return true;
    }

}
