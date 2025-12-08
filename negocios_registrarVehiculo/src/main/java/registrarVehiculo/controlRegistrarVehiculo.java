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

    protected void confirmarRegistroVehiculoPropietario() throws Exception {
        ObjectId vehiculoId = new ObjectId();
        vehiculoTemp.setId(vehiculoId.toHexString());

        this.ListaVehiculosTemp.add(vehiculoTemp);
        this.propietarioTemp.setListaVehiculos(ListaVehiculosTemp);

        PropietarioHaciendaDTO propHaciendaDTO = toPropietarioHaciendaDTO(propietarioTemp);
        VehiculoHaciendaDTO vehHaciendaDTO = toVehiculoHaciendaDTO(vehiculoTemp);

        System.out.println(propHaciendaDTO.getCurp());
        System.out.println(vehHaciendaDTO.getNumeroSerie());

        boolean datosValidos = validacionHaciendaService.verificarCoincidencia(
                propHaciendaDTO, // DTO del Propietario
                vehHaciendaDTO // DTO del Vehículo
        );
        if (!datosValidos) {
            throw new Exception("Error de validación: Datos no coinciden con Hacienda.");
        }

        conductorBO.agregarVehiculo(vehiculoTemp);
        vehiculoBO.registrarVehiculo(vehiculoTemp);
        propietarioBO.registrarPropietario(propietarioTemp);

    }

    public static PropietarioHaciendaDTO toPropietarioHaciendaDTO(PropietarioDTO propietarioDTO) {
        if (propietarioDTO == null) {
            return null;
        }

        // Creamos el DTO de destino
        PropietarioHaciendaDTO haciendaDTO = new PropietarioHaciendaDTO();

        // Copiamos los campos relevantes para la validación de Hacienda
        haciendaDTO.setCurp(propietarioDTO.getCurp());
        haciendaDTO.setNombre(propietarioDTO.getNombre());
        haciendaDTO.setRfc(propietarioDTO.getRfc());
        haciendaDTO.setNss(propietarioDTO.getNss());

        // Nota: Los vehículos se mapean por separado o se asume que solo necesitamos los datos del propietario.
        // Si HaciendaDTO necesita la lista de vehículos, aquí deberías añadir:
        // haciendaDTO.setVehiculos(mapVehiculos(propietarioDTO.getListaVehiculos()));
        return haciendaDTO;
    }

    /**
     * Convierte un VehiculoDTO de la capa de Negocio/Control a un
     * VehiculoHaciendaDTO que es usado por el servicio de validación de
     * Hacienda.
     *
     * * @param vehiculoDTO El DTO del vehículo desde el Control/UI.
     * @return El DTO específico para la capa de Hacienda.
     */
    public static VehiculoHaciendaDTO toVehiculoHaciendaDTO(VehiculoDTO vehiculoDTO) {
        if (vehiculoDTO == null) {
            return null;
        }

        VehiculoHaciendaDTO haciendaDTO = new VehiculoHaciendaDTO();

        // Copiamos los campos relevantes para la validación de Hacienda
        haciendaDTO.setNumeroSerie(vehiculoDTO.getNumeroSerie());
        haciendaDTO.setPlacas(vehiculoDTO.getPlacas());
        haciendaDTO.setMarca(vehiculoDTO.getMarca());
        haciendaDTO.setModelo(vehiculoDTO.getModelo());
        haciendaDTO.setColor(vehiculoDTO.getColor());
        haciendaDTO.setCapacidad(vehiculoDTO.getCapacidad());

        return haciendaDTO;
    }
}
