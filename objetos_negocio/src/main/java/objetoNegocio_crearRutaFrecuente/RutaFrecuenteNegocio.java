/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio_crearRutaFrecuente;

import dto.ParadaDTO;
import dto.RutaFrecuenteDTO;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;
import org.base_datos_viajes.dao.interfaces.IRutaFrecuenteDAO;

import java.util.List;
import org.base_datos_viajes.model.RutaFrecuente;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *clase que implementa la interfaz de crear ruta frecuente negocio
 * @author adell
 */
public class RutaFrecuenteNegocio implements ICrearRutaFrecuenteNegocio {

    private final IRutaFrecuenteDAO rutaDAO;

    public RutaFrecuenteNegocio(IRutaFrecuenteDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
    }
    /**
     * metodo que se encarga de persistir la ruta
     * @param ruta rutadto donde se van a extraer los datos
     */
    @Override
    public void registrarRuta(RutaFrecuenteDTO ruta) {
        ObjectId idConductor = new ObjectId(SesionUsuario.obtenerConductor().getId());

        RutaFrecuente entity = adaptadores.adaptadorRutaFrecuente.toEntity(ruta, idConductor);
        rutaDAO.save(entity);
    }
    /**
     * metodo que se encarga de obtener las paradas de una rutafrecuente dto
     * @param ruta objeto rutafrecuente dto
     * @return devuelve una lista de paradas dto
     */
    @Override
    public List<ParadaDTO> obtenerParadasDTO(RutaFrecuenteDTO ruta) {
        return ruta.getParadas();
    }
    
    /**
     * metodo que se encarga de asignarle una paradadto a una ruta frecuente dto
     * @param ruta ruta a la que se le insertara la parada
     * @param parada parada que sera insertada en la ruta
     */
    @Override
    public void RegistrarParada(RutaFrecuenteDTO ruta, ParadaDTO parada) {
        ruta.getParadas().add(parada);
    }
   
    
    /**
     * metodo que se encarga de validar que no exista una rutafrecuente igual ya creada
     * @param rutas lista de rutas creadas
     * @param ruta ruta a comparar
     * @return true o false que se dispara  si existe o no
     */
    @Override
    public boolean validarNoExisteRuta(List<RutaFrecuenteDTO> rutas, RutaFrecuenteDTO ruta) {
        for (RutaFrecuenteDTO r : rutas) {
            if (ruta.equals(r)) {
                return false;
            }
        }
        return true;
    }

}
