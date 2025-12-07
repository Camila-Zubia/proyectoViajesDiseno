/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarVehiculo;

/**
 *
 * @author adell
 */
public class FRegistrarVehiculo implements IRegistrarVehiculo {

    private final controlRegistrarVehiculo controlVehiculo;

    public FRegistrarVehiculo() {
        controlVehiculo = new controlRegistrarVehiculo();
    }

    @Override
    public void guardarDatosVehiculo(String modelo, String placas, String marca, String color, int capacidad) {
        controlVehiculo.guardarDatosVehiculo(modelo, placas, marca, color, capacidad);
    }

    @Override
    public void guardarDatosPropietario(String nombre, String curp, String rfc, String nss) {
        controlVehiculo.guardarDatosPropietario(nombre, curp, rfc, nss);
    }

    @Override
    public void confirmarRegistroVehiculoPropietario() {
        controlVehiculo.confirmarRegistroVehiculoPropietario();
    }
}
