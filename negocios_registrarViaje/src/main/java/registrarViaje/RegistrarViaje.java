/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarViaje;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.util.List;
import objetosNegocio.ParadaNegocio;
import objetosNegocio.VehiculoNegocio;
import objetosNegocio.ViajeNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class RegistrarViaje implements IRegistrarViaje{
    
    private ViajeNegocio viajeBO;
    private ParadaNegocio paradaBO;
    private VehiculoNegocio vehiculoBO;

    public RegistrarViaje() {
        this.viajeBO = new ViajeNegocio();
        this.paradaBO = new ParadaNegocio();
        this.vehiculoBO = new VehiculoNegocio();
    }

    @Override
    public void crearViaje(ViajeDTO viaje) {
        viajeBO.registrarViaje(viaje);
    }

    @Override
    public List<VehiculoDTO> obtenerVehiculos(ConductorDTO conductor) {
        return vehiculoBO.obtenerVehiculos();
    }

    @Override
    public void crearParada(ParadaDTO parada) {
        paradaBO.registrarParada(parada);
    }

    @Override
    public List<ViajeDTO> obtenerViajes(ConductorDTO conductor) {
        return viajeBO.obtenerViajes(conductor);
    }

    @Override
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje) {
        return paradaBO.obtenerParadas();
    }

}
