/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package registrarVehiculo;

/**
 *
 * @author adell
 */
public interface IRegistrarVehiculo {

    public void guardarDatosVehiculo(String modelo, String placas, String marca, String color, int capacidad);

    public void guardarDatosPropietario(String nombre, String curp, String rfc, String nss);

    public void confirmarRegistroVehiculoPropietario();
}
