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
    private List<ViajeDTO> viajesMock = new ArrayList<>();

    public RegistrarViaje() {
        vehiculosMock.add(new VehiculoDTO("Civic 2020", "ABC-123", "Honda", "Blanco", 4));
        vehiculosMock.add(new VehiculoDTO("Corolla 2021", "XYZ-789", "Toyota", "Gris", 4));
        vehiculosMock.add(new VehiculoDTO("Jetta 2019", "DEF-456", "Volkswagen", "Negro", 4));

        inicializarViajesMock();
    }

    private void inicializarViajesMock() {
        List<ParadaDTO> paradas1 = new ArrayList<>();
        paradas1.add(new ParadaDTO("Tutuli", 50.0));
        viajesMock.add(new ViajeDTO("Obregón", "Navojoa", new java.util.Date(),
                java.time.LocalTime.of(10, 30), 250.0, paradas1));

        List<ParadaDTO> paradas2 = new ArrayList<>();
        paradas2.add(new ParadaDTO("ITSON", 30.0));
        paradas2.add(new ParadaDTO("Central camiones", 40.0));
        viajesMock.add(new ViajeDTO("Obregon", "Esperanza", new java.util.Date(),
                java.time.LocalTime.of(14, 0), 320.0, paradas2));
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

    @Override
    public List<ViajeDTO> obtenerViajes(String nombreConductor) {
        if (nombreConductor == null || nombreConductor.isEmpty()) {
            System.out.println("MOCK: Nombre de conductor inválido");
            return new ArrayList<>();
        }

        System.out.println("MOCK: Obteniendo viajes del conductor: " + nombreConductor);
        System.out.println("MOCK: Retornando " + viajesMock.size() + " viajes");
        return new ArrayList<>(viajesMock);
    }

}
