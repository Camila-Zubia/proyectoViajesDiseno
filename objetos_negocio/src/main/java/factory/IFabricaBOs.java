/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory;

import interfaces.IConductorNegocio;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;
import interface_crearRutaFrecuente.ICrearRutaFrecuenteNegocio;
import interfaces_cancelarViaje.IAdeudoNegocio;
import interfaces_editarViaje.IEditarViajeNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IFabricaBOs {
    
    public IUsuarioNegocio crearUsuarioNegocio();
    
    public IConductorNegocio crearConductorNegocio();
    
    public IViajeNegocio crearViajeNegocio();
    
    
    //metodos del caso de uso "Solicitar Reservacion" y "Cancelar Reservacion"
    public IPasajeroNegocio crearPasajeroNegocio();
    
    //metodo para el caso de uso crear ruta frecuente
    public ICrearRutaFrecuenteNegocio crearRutaFrecuenteNegocio();

    //metodos del caso de uso "Cancelar Viaje"
    public IAdeudoNegocio crearAdeudoNegocio();
    
    //metodos par el caso de uso editarViaje
     public IEditarViajeNegocio crearEditarViajeNegocio();

}
