/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarViaje;

import dto.ParadaDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class RegistrarViaje implements IRegistrarViaje{

    private List<ParadaDTO> paradasTemp = new ArrayList<>();
    private List<VehiculoDTO> vehiculosMock = new ArrayList<>();

    public RegistrarViaje() {
        vehiculosMock.add(new VehiculoDTO("Civic 2020", "ABC-123", "Honda", "Blanco", 4));
        vehiculosMock.add(new VehiculoDTO("Corolla 2021", "XYZ-789", "Toyota", "Gris", 4));
        vehiculosMock.add(new VehiculoDTO("Jetta 2019", "DEF-456", "Volkswagen", "Negro", 4));
    }

    @Override
    public void crearViaje(ViajeDTO viaje) {
        System.out.println("MOCK: Viaje creado exitosamente");
        System.out.println("Origen: " + viaje.getOrigen());
        System.out.println("Destino: " + viaje.getDestino());
        System.out.println("Fecha: " + viaje.getFecha());
        System.out.println("Hora: " + viaje.getHora());
    }

    @Override
    public VehiculoDTO obtenerVehiculo(VehiculoDTO vehiculo) {
        System.out.println("MOCK: Vehiculo obtenido: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        return vehiculo;
    }

    @Override
    public List<VehiculoDTO> obtenerVehiculos() {
        System.out.println("MOCK: Retornando " + vehiculosMock.size() + " vehiculos");
        return new ArrayList<>(vehiculosMock);
    }

    @Override
    public boolean validarNoExiste() {
        System.out.println("MOCK: Validacion exitosa - viaje no existe en el sistema");
        return false;
    }

    @Override
    public void agregarAListaParadas(List<ParadaDTO> paradas) {
        if (paradas != null) {
            paradasTemp.addAll(paradas);
            System.out.println("MOCK: Se agregaron " + paradas.size() + " paradas. Total: " + paradasTemp.size());
        }
    }

    @Override
    public void crearParada(ParadaDTO parada) {
        if (parada != null) {
            paradasTemp.add(parada);
            System.out.println("MOCK: Parada creada - Direccion: " + parada.getDirección() + ", Precio: $" + parada.getPrecio());
        }
    }

}
