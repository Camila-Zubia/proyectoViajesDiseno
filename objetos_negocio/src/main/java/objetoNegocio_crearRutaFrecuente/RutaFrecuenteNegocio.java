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

/**
 *
 * @author adell
 */
public class RutaFrecuenteNegocio implements ICrearRutaFrecuenteNegocio {

    private final IRutaFrecuenteDAO rutaDAO;

    public RutaFrecuenteNegocio(IRutaFrecuenteDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
    }

    @Override
    public void registrarRuta(RutaFrecuenteDTO ruta) {
        //falta añadir adaptador para convertir la ruta a la entity
        // rutaDAO.save(entity);
    }

    @Override
    public List<ParadaDTO> obtenerParadasDTO(RutaFrecuenteDTO ruta) {
        return ruta.getParadas();
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RegistrarParada(RutaFrecuenteDTO ruta, ParadaDTO parada) {
        ruta.getParadas().add(parada);
    }

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
