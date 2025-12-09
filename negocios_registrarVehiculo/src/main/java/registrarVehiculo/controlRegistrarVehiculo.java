/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarVehiculo;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;
import IObjetoNegocio.IValidacionPropietarioVehiculoServicio;
import dto.PropietarioDTO;
import dto.VehiculoDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interface_registrarVehiculo.IPropietarioNegocio;
import interface_registrarVehiculo.IVehiculoNegocio;
import interfaces.IConductorNegocio;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author adell
 */
public class controlRegistrarVehiculo {

    private final IPropietarioNegocio propietarioBO;
    private final IVehiculoNegocio vehiculoBO;
    private final IConductorNegocio conductorBO;

    private final VehiculoDTO vehiculoTemp;
    private final PropietarioDTO propietarioTemp;
    private final List<VehiculoDTO> ListaVehiculosTemp;
    private final IValidacionPropietarioVehiculoServicio validacionHaciendaService;

    public controlRegistrarVehiculo() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.propietarioBO = fabrica.crearPropietarioNegocio();
        this.vehiculoBO = fabrica.crearVehiculoNegocio();
        this.conductorBO = fabrica.crearConductorNegocio();

        this.validacionHaciendaService = fabrica.crearValidacionHaciendaServicio();

        this.vehiculoTemp = new VehiculoDTO();
        this.propietarioTemp = new PropietarioDTO();
        this.ListaVehiculosTemp = new ArrayList<>();
    }

    protected void guardarDatosVehiculo(String numeroSerie, String modelo, String placas, String marca, String color, int capacidad) {
        this.vehiculoTemp.setNumeroSerie(numeroSerie);
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

    protected boolean confirmarRegistroVehiculoPropietario() throws Exception {
        ObjectId vehiculoId = new ObjectId();
        vehiculoTemp.setId(vehiculoId.toHexString());

        this.ListaVehiculosTemp.add(vehiculoTemp);
        this.propietarioTemp.setListaVehiculos(ListaVehiculosTemp);

        PropietarioHaciendaDTO propHaciendaDTO = adaptadores.adaptadorHacienda.toPropietarioHaciendaDTO(propietarioTemp);
        VehiculoHaciendaDTO vehHaciendaDTO = adaptadores.adaptadorHacienda.toVehiculoHaciendaDTO(vehiculoTemp);

        boolean datosValidos = validacionHaciendaService.verificarCoincidencia(
                propHaciendaDTO, // DTO del Propietario
                vehHaciendaDTO // DTO del Vehículo
        );
        if (!datosValidos) {
            System.err.println("Error de validación: Datos no coinciden con Hacienda.");
            return false;
        }

        conductorBO.agregarVehiculo(vehiculoTemp);
        vehiculoBO.registrarVehiculo(vehiculoTemp);
        propietarioBO.registrarPropietario(propietarioTemp);

        return true;
    }

}
