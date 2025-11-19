/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import interfaces.IConductorNegocio;
import interfaces.IUsuarioNegocio;
import interfaces.IViajeNegocio;
import objetosNegocio.ConductorNegocio;
import objetosNegocio.UsuarioNegocio;
import objetosNegocio.ViajeNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class FabricaBOs implements IFabricaBOs{
    
    private static IUsuarioNegocio instancia;

    public FabricaBOs() {
    }

    @Override
    public IUsuarioNegocio crearUsuarioNegocio() {
        if (instancia == null) {
            instancia = new UsuarioNegocio();
        }
        return instancia;
    }

    @Override
    public IConductorNegocio crearConductorNegocio() {
        return new ConductorNegocio();
    }

    @Override
    public IViajeNegocio crearViajeNegocio() {
        return new ViajeNegocio();
    }
}
