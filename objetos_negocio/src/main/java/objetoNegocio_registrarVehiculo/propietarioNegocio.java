/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio_registrarVehiculo;

import dto.PropietarioDTO;
import org.base_datos_viajes.model.Propietario;
import org.base_datos_viajes.dao.interfaces.IPropietarioDAO;

/**
 *
 * @author adell
 */
public class propietarioNegocio implements interface_registrarVehiculo.IPropietarioNegocio {

    private final IPropietarioDAO propietarioDAO;

    public propietarioNegocio(IPropietarioDAO propietarioDAO) {
        this.propietarioDAO = propietarioDAO;
    }

    @Override
    public void registrarPropietario(PropietarioDTO propietario) {
        Propietario entity = adaptadores.adaptadorPropietario.toEntity(propietario);
        propietarioDAO.save(entity);
    }

}
