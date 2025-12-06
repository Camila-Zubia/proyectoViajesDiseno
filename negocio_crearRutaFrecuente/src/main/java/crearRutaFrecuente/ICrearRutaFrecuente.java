/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface ICrearRutaFrecuente {

    public void GuardarDatosRutaFrec(String nombre, String origen, String destino, LocalDate fecha, LocalTime hora);

    public List<RutaFrecuenteDTO> obtenerRutaPorConductor(ConductorDTO conductor);

    public void agregarParada(String direccion, double precio);

    public List<ParadaDTO> obtenerParadas(RutaFrecuenteDTO ruta);

    public List<ParadaDTO> obtenerParadasTemp();

    public RutaFrecuenteDTO confirmaRuta();

}
