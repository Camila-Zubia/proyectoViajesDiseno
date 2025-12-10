/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import IObjetoNegocio.IValidacionPropietarioVehiculoServicio;
import interfaces.IConductorNegocio;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;
import objetoNegocio_crearRutaFrecuente.RutaFrecuenteNegocio;
import interfaces_cancelarViaje.IAdeudoNegocio;
import objetosNegocio.ConductorNegocio;
import objetosNegocio.UsuarioNegocio;
import objetosNegocio.ViajeNegocio;
import objetosNegocio_solicitarReservacion.PasajeroNegocio;
import objetosNegocio_cancelarViaje.AdeudoNegocio;
import org.base_datos_viajes.dao.impl.ConductorDAO;
import org.base_datos_viajes.dao.impl.UsuarioDAO;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;
import interface_registrarVehiculo.IPropietarioNegocio;
import interface_registrarVehiculo.IVehiculoNegocio;
import interfaces_editarViaje.IEditarViajeNegocio;
import interfaces_gestionarSolicitudes.IGestionarSolicitudesNegocio;
import interfaces_solicitarReservacion.IReservacionNegocio;
import objetoNegocio.ValidacionPropietarioVehiculoServicio;
import objetoNegocio_registrarVehiculo.propietarioNegocio;
import objetoNegocio_registrarVehiculo.vehiculoNegocio;
import objetosNegocio_editarViaje.EditarViajeNegocio;
import objetosNegocio_gestionarSolicitudes.GestionarSolicitudesNegocio;
import objetosNegocio_solicitarReservacion.ReservacionNegocio;
import org.base_datos_viajes.dao.impl.PasajeroDAO;
import org.base_datos_viajes.dao.impl.ReservacionDAO;
import org.base_datos_viajes.dao.impl.RutasFrecuentesDAO;
import org.base_datos_viajes.dao.impl.VehiculoDAO;
import org.base_datos_viajes.dao.impl.PropietarioDAO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class FabricaBOs implements IFabricaBOs {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ViajeDAO viajeDAO = new ViajeDAO();
    private final ConductorDAO conductorDAO = new ConductorDAO();
    private final PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private final ReservacionDAO reservacionDAO = new ReservacionDAO();
    private final RutasFrecuentesDAO rutaDAO = new RutasFrecuentesDAO();
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final PropietarioDAO propietarioDAO = new PropietarioDAO();

    public FabricaBOs() {
    }

    @Override
    public IUsuarioNegocio crearUsuarioNegocio() {
        return new UsuarioNegocio(usuarioDAO, conductorDAO, pasajeroDAO);
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
    public IPasajeroNegocio crearPasajeroNegocio() {
        return new PasajeroNegocio(pasajeroDAO, reservacionDAO);
    }

    @Override
    public IReservacionNegocio crearReservacionNegocio() {
        return new ReservacionNegocio(reservacionDAO);
    }
    
    //metodo del caso de uso crear ruta frecuente
    /**
     * Crea una nueva instancia del objeto de la capa de Negocio (Servicio) para
     * el caso de uso 'Registrar Ruta Frecuente'. * Este método se encarga de
     * inyectar el objeto DAO necesario para que la lógica de negocio pueda
     * interactuar con la persistencia.
     *
     * * @return Implementación de la interfaz ICrearRutaFrecuenteNegocio.
     */
    @Override
    public ICrearRutaFrecuenteNegocio crearRutaFrecuenteNegocio() {
        return new RutaFrecuenteNegocio(rutaDAO);
    }

    //metodos del caso de uso "Cancelar Viaje"
    @Override
    public IAdeudoNegocio crearAdeudoNegocio() {
        return new AdeudoNegocio();
    }

    //metodos del caso de uso editar viaje
    @Override
    public IEditarViajeNegocio crearEditarViajeNegocio() {
        return new EditarViajeNegocio(viajeDAO, reservacionDAO);
    }

    //metodos del caso de uso registra vehiculo
    @Override
    public IPropietarioNegocio crearPropietarioNegocio() {
        return new propietarioNegocio(propietarioDAO);
    }

    @Override
    public IVehiculoNegocio crearVehiculoNegocio() {
        return new vehiculoNegocio(vehiculoDAO);
    }

    //metodos del caso de uso de gestionar solicitudes
    @Override
    public IGestionarSolicitudesNegocio crearGestionarSolicitudesNegocio() {
        return new GestionarSolicitudesNegocio(viajeDAO, reservacionDAO, conductorDAO);
        }
    /**
     * Implementa el patrón Factory Method para crear una instancia del servicio
     * de validación de Hacienda. * 
     * * @return Una interfaz (IValidacionPropietarioVehiculoServicio) que
     * contiene los métodos para interactuar con la simulación de la API de
     * validación externa.
     */

    @Override
    public IValidacionPropietarioVehiculoServicio crearValidacionHaciendaServicio() {

        return new ValidacionPropietarioVehiculoServicio();

    }
}
