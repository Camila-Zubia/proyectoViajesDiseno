/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio_registrarVehiculo;

import org.base_datos_viajes.dao.interfaces.IPropietarioDAO;

/**
 *
 * @author adell
 */
public class propietarioNegocio {

    private final IPropietarioDAO propietarioDAO;

    public propietarioNegocio(IPropietarioDAO propietarioDAO) {
        this.propietarioDAO = propietarioDAO;
    }

}
