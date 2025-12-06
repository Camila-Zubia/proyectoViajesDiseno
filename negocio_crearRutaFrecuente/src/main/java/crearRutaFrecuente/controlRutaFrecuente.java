/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crearRutaFrecuente;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;
import interfaces.IConductorNegocio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adell
 */
public class controlRutaFrecuente {

    private final ICrearRutaFrecuenteNegocio rutaFrecuenteBO;
    private final RutaFrecuenteDTO rutaFrecuente;
    private final IConductorNegocio conductorBO;
    private final List<ParadaDTO> paradasTemp;

    public controlRutaFrecuente() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.rutaFrecuenteBO = fabrica.crearRutaFrecuenteNegocio();
        this.conductorBO = fabrica.crearConductorNegocio();
        this.rutaFrecuente = new RutaFrecuenteDTO();
        this.paradasTemp = new ArrayList<>();
    }

    //rutas frecc
    protected void GuardarDatosRutaFrec(String nombre, String origen, String destino, LocalDate fecha, LocalTime hora) {
        this.rutaFrecuente.setNombre(nombre);
        this.rutaFrecuente.setOrigen(origen);
        this.rutaFrecuente.setDestino(destino);
        this.rutaFrecuente.setFecha(fecha);
        this.rutaFrecuente.setHora(hora);

    }
    
    protected List<RutaFrecuenteDTO> obtenerRutaPorConductor(ConductorDTO conductor){
        return conductorBO.obtenerRutas();
    }
    
    //paradas
    protected void agregarParada(String direccion, double precio) {
        ParadaDTO parada = new ParadaDTO(direccion, precio);
        paradasTemp.add(parada);
    }

    protected List<ParadaDTO> obtenerParadas(RutaFrecuenteDTO ruta) {
        return rutaFrecuenteBO.obtenerParadasDTO(ruta);
    }

    protected List<ParadaDTO> obtenerParadasTemp() {
        return paradasTemp;
    }

    //confirmacion de la ruta frecuente
    protected RutaFrecuenteDTO confirmaRuta() {
        double contador = 0;
        for (ParadaDTO p : paradasTemp) {
            contador = p.getPrecio() + p.getPrecio();
        }
        rutaFrecuente.setPrecioTotal(contador);
        rutaFrecuente.setParadas(paradasTemp);
        rutaFrecuenteBO.registrarRuta(rutaFrecuente);
        paradasTemp.clear();
        return rutaFrecuente;
    }

}
