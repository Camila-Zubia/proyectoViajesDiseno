/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarVehiculo;

import dto.PropietarioDTO;
import dto.VehiculoDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interface_registrarVehiculo.IPropietarioNegocio;
import interface_registrarVehiculo.IVehiculoNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adell
 */
public class controlRegistrarVehiculo {
    
    private final IPropietarioNegocio propietarioBO;
    private final IVehiculoNegocio vehiculoBO;
    private final VehiculoDTO vehiculoTemp;
    private final PropietarioDTO propietarioTemp;
    private final List<VehiculoDTO> ListaVehiculosTemp;
    
    public controlRegistrarVehiculo() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.propietarioBO = fabrica.crearPropietarioNegocio();
        this.vehiculoBO = fabrica.crearVehiculoNegocio();
        
        this.vehiculoTemp = new VehiculoDTO();
        this.propietarioTemp = new PropietarioDTO();
        this.ListaVehiculosTemp = new ArrayList<>();
    }
    
    protected void guardarDatosVehiculo(String modelo, String placas, String marca, String color, int capacidad) {
        this.vehiculoTemp.setModelo(modelo);
        this.vehiculoTemp.setPlacas(placas);
        this.vehiculoTemp.setMarca(marca);
        this.vehiculoTemp.setColor(color);
        this.vehiculoTemp.setCapacidad(capacidad);  
    }
    
    protected void guardarDatosPropietario(String nombre, String curp, String rfc, String nss) {
        this.propietarioTemp.setNombre(nombre);
        this.propietarioTemp.setCurp(curp);
        this.propietarioTemp.setRfc(rfc);
        this.propietarioTemp.setNss(nss);
    }
    
    protected void confirmarRegistroVehiculoPropietario(){
        this.ListaVehiculosTemp.add(vehiculoTemp);
        this.propietarioTemp.setListaVehiculos(ListaVehiculosTemp);
        
        vehiculoBO.registrarVehiculo(vehiculoTemp);
        propietarioBO.registrarPropietario(propietarioTemp);
    
    }
}
