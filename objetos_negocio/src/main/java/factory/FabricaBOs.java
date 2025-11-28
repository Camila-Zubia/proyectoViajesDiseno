/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import interface_crearRutaFrecuente.ICrearRutaFrecuente;
import interfaces.IConductorNegocio;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import interfaces_solicitarReservacion.IReservacionNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;
import objetoNegocio_crearRutaFrecuente.RutaFrecuenteNegocio;
import objetosNegocio.ConductorNegocio;
import objetosNegocio.UsuarioNegocio;
import objetosNegocio.ViajeNegocio;
import objetosNegocio_solicitarReservacion.PasajeroNegocio;
import objetosNegocio_solicitarReservacion.ReservacionNegocio;
import org.base_datos_viajes.dao.impl.ConductorDAO;
import org.base_datos_viajes.dao.impl.UsuarioDAO;
import org.base_datos_viajes.dao.impl.ViajeDAO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class FabricaBOs implements IFabricaBOs{
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ViajeDAO viajeDAO = new ViajeDAO();
    private final ConductorDAO conductorDAO = new ConductorDAO();

    public FabricaBOs() {
    }

    @Override
    public IUsuarioNegocio crearUsuarioNegocio() {
          return new UsuarioNegocio(usuarioDAO, conductorDAO);
    }

    @Override
    public IConductorNegocio crearConductorNegocio() {
        return new ConductorNegocio(conductorDAO, usuarioDAO, viajeDAO);
    }

    @Override
    public IViajeNegocio crearViajeNegocio() {
        return new ViajeNegocio(viajeDAO, conductorDAO);
    }

    //metodos del caso de uso "Solicitar Reservacion"
    @Override
    public IReservacionNegocio crearReservacionNegocio() {
        return new ReservacionNegocio();
    }

    @Override
    public IPasajeroNegocio crearPasajeroNegocio() {
        return new PasajeroNegocio();
    }
    
    //metodos del caso de uso registrar ruta frecuente
    public ICrearRutaFrecuente crearRutaFrecuenteNegocio(){
        return new  RutaFrecuenteNegocio();
    }
}
