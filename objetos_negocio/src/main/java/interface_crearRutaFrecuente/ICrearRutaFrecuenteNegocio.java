/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_crearRutaFrecuente;

import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import java.util.List;

/**
 * interfaz que tiene los metodos de vehiculo negocio
 *
 * @author adell
 */
public interface ICrearRutaFrecuenteNegocio {

    /**
     * metodo que implementa la persistencia de una ruta
     *
     * @param ruta objeto a persistir
     */
    public void registrarRuta(RutaFrecuenteDTO ruta);

    /**
     * metodo que implementa para obtener las paradas de una ruta
     *
     * @param ruta objeto de donde se recuperaran las paradas
     * @return lista de paradas
     */
    public List<ParadaDTO> obtenerParadasDTO(RutaFrecuenteDTO ruta);

    /**
     * metodos que implmenta la persistencia de una parada en una ruta
     *
     * @param ruta objeto donde se persistiran las paradas
     * @param parada objeto que se persisitira en ruta
     */
    public void RegistrarParada(RutaFrecuenteDTO ruta, ParadaDTO parada);

    /**
     * metodo que implementa la validacion de que no existe una ruta ya creada
     * igual
     *
     * @param rutas lista de rutas a comparar
     * @param ruta ruta que se comparara
     * @return true or false dependiendo si existe o no
     */
    public boolean validarNoExisteRuta(List<RutaFrecuenteDTO> rutas, RutaFrecuenteDTO ruta);

    public boolean eliminarRuta(RutaFrecuenteDTO ruta);
    
    public List<RutaFrecuenteDTO> obtenerRutas();

}
