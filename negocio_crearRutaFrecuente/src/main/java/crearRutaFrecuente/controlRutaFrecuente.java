/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crearRutaFrecuente;

import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;

/**
 *
 * @author adell
 */
public class controlRutaFrecuente {

    private final ICrearRutaFrecuenteNegocio rutaFrecuenteBO;
    private RutaFrecuenteDTO rutaFrecuente;

    public controlRutaFrecuente() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.rutaFrecuenteBO = fabrica.crearRutaFrecuenteNegocio();
    }

    public void RegistrarRutaFrec(RutaFrecuenteDTO ruta) {

        rutaFrecuenteBO.registrarRuta(ruta);
    }

    public void agregarParada(String direccion, double precio) {
        ParadaDTO parada = new ParadaDTO(direccion, precio);
        rutaFrecuenteBO.RegistrarParada(rutaFrecuente, parada);
    }
}
