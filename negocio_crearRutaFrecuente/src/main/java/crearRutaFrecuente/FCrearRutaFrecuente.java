/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crearRutaFrecuente;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author adell
 */
public class FCrearRutaFrecuente implements ICrearRutaFrecuente {

    private final controlRutaFrecuente controlRuta;

    public FCrearRutaFrecuente() {
        controlRuta = new controlRutaFrecuente();
    }

    @Override
    public void GuardarDatosRutaFrec(String nombre, String origen, String destino, LocalDate fecha, LocalTime hora) {
        controlRuta.GuardarDatosRutaFrec(nombre, origen, destino, fecha, hora);
    }

    @Override
    public List<RutaFrecuenteDTO> obtenerRutaPorConductor(ConductorDTO conductor) {
        return controlRuta.obtenerRutaPorConductor(conductor);
    }

    @Override
    public void agregarParada(String direccion, double precio) {
        controlRuta.agregarParada(direccion, precio);
    }

    @Override
    public List<ParadaDTO> obtenerParadas(RutaFrecuenteDTO ruta) {
        return controlRuta.obtenerParadas(ruta);
    }

    @Override
    public List<ParadaDTO> obtenerParadasTemp() {
        return controlRuta.obtenerParadasTemp();
    }

    @Override
    public RutaFrecuenteDTO confirmaRuta() {
        return controlRuta.confirmaRuta();
    }

}
