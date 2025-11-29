/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_crearRutaFrecuente;

import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICrearRutaFrecuenteNegocio {

    public void registrarRuta(RutaFrecuenteDTO ruta);

    public List<ParadaDTO> obtenerParadasDTO(RutaFrecuenteDTO ruta);

    public void RegistrarParada(RutaFrecuenteDTO ruta, ParadaDTO parada);

    public boolean validarNoExisteRuta(List<RutaFrecuenteDTO> rutas, RutaFrecuenteDTO ruta);

}
