/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory;

import interfaces.IConductorNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IFabricaBOs {
    
    public IUsuarioNegocio crearUsuarioNegocio();
    
    public IConductorNegocio crearConductorNegocio();
    
    public IViajeNegocio crearViajeNegocio();
    
    
}
