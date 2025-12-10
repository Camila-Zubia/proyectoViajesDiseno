/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory;

import IObjetoNegocio.IValidacionPropietarioVehiculoServicio;
import interfaces.IConductorNegocio;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;
import interface_registrarVehiculo.IPropietarioNegocio;
import interface_registrarVehiculo.IVehiculoNegocio;
import interfaces_cancelarViaje.IAdeudoNegocio;
import interfaces_editarViaje.IEditarViajeNegocio;
import interfaces_gestionarSolicitudes.IGestionarSolicitudesNegocio;
import interfaces_solicitarReservacion.IReservacionNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IFabricaBOs {

    /**
     * Crea y retorna la lógica de negocio para gestionar un Usuario.
     *
     * @return Implementación de la interfaz IUsuarioNegocio.
     */
    public IUsuarioNegocio crearUsuarioNegocio();

    /**
     * Crea y retorna la lógica de negocio para gestionar un Conductor.
     *
     * @return Implementación de la interfaz IConductorNegocio.
     */
    public IConductorNegocio crearConductorNegocio();

    /**
     * Crea y retorna la lógica de negocio para gestionar un Viaje.
     *
     * @return Implementación de la interfaz IViajeNegocio.
     */
    public IViajeNegocio crearViajeNegocio();

    //metodos del caso de uso "Solicitar Reservacion" y "Cancelar Reservacion"
    /**
     * Crea y retorna la lógica de negocio para el caso de uso "Solicitar
     * Reservación" (Pasajero).
     *
     * @return Implementación de la interfaz IPasajeroNegocio.
     */
    public IPasajeroNegocio crearPasajeroNegocio();

    /**
     * Crea y retorna la lógica de negocio para gestionar Reservaciones.
     *
     * @return Implementación de la interfaz IReservacionNegocio.
     */
    public IReservacionNegocio crearReservacionNegocio();

    /**
     * Crea y retorna la lógica de negocio para el caso de uso "Crear Ruta
     * Frecuente".
     *
     * @return Implementación de la interfaz ICrearRutaFrecuenteNegocio.
     */
    public ICrearRutaFrecuenteNegocio crearRutaFrecuenteNegocio();

    //metodos del caso de uso "Cancelar Viaje"
    /**
     * Crea y retorna la lógica de negocio para el caso de uso "Cancelar Viaje"
     * (Adeudo).
     *
     * @return Implementación de la interfaz IAdeudoNegocio.
     */
    public IAdeudoNegocio crearAdeudoNegocio();

    //metodos par el caso de uso editarViaje
    /**
     * Crea y retorna la lógica de negocio para el caso de uso "Editar Viaje".
     *
     * @return Implementación de la interfaz IEditarViajeNegocio.
     */
    public IEditarViajeNegocio crearEditarViajeNegocio();

    //metodos para el caso de uso registrar vehiculo
    /**
     * Crea y retorna la lógica de negocio para gestionar Propietarios (Registro
     * de Vehículo).
     *
     * @return Implementación de la interfaz IPropietarioNegocio.
     */
    public IPropietarioNegocio crearPropietarioNegocio();

    /**
     * Crea y retorna la lógica de negocio para gestionar Vehículos (Registro de
     * Vehículo).
     *
     * @return Implementación de la interfaz IVehiculoNegocio.
     */
    public IVehiculoNegocio crearVehiculoNegocio();

    
    //metodos para el caso de uso de gestionar solicitudes
    public IGestionarSolicitudesNegocio crearGestionarSolicitudesNegocio();

    /**
     * Crea y retorna la implementación del Servicio que simula la validación
     * con la API externa (Hacienda). Este método es crucial para la validación
     * de datos de propietario y vehículo.
     *
     * @return Implementación de la interfaz
     * IValidacionPropietarioVehiculoServicio.
     */
    public IValidacionPropietarioVehiculoServicio crearValidacionHaciendaServicio();

}
