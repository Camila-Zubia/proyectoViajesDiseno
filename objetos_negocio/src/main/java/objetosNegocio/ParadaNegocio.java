/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ParadaDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ParadaNegocio {

    ViajeNegocio viajeBO = new ViajeNegocio();

    public ParadaNegocio() {
        viajeBO.obtenerParadas().add(new ParadaDTO("Tutuli", 50.0));
        viajeBO.obtenerParadas().add(new ParadaDTO("ITSON", 30.0));
        viajeBO.registrarParada(new ParadaDTO("Central camiones", 40.0));
    }
    
    public List<ParadaDTO> obtenerParadas(){
        return viajeBO.obtenerParadas();
    } 
    
    public void registrarParada(ParadaDTO parada){
        viajeBO.registrarParada(parada);
    }
    
}
