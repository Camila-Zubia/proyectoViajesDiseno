/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adaptadores.adaptadorConductor;
import adaptadores.adaptadorPasajero;
import adaptadores.adaptadorUsuario;
import dto.UsuarioDTO;
import interfaces.IUsuarioNegocio;
import java.util.Optional;
import org.base_datos_viajes.dao.impl.UsuarioDAO;
import org.base_datos_viajes.dao.interfaces.IConductorDAO;
import org.base_datos_viajes.dao.interfaces.IPasajeroDAO;
import exceptiones.DatabaseException;
import org.base_datos_viajes.model.Conductor;
import org.base_datos_viajes.model.Pasajero;
import org.base_datos_viajes.model.Usuario;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class UsuarioNegocio implements IUsuarioNegocio{

    private final UsuarioDAO usuarioDAO; 
    private final IConductorDAO conductorDAO;
    private final IPasajeroDAO pasajeroDAO;
    
    public UsuarioNegocio(UsuarioDAO usuarioDAO, IConductorDAO conductorDAO, IPasajeroDAO pasajeroDAO) { 
        this.usuarioDAO = usuarioDAO;
        this.conductorDAO = conductorDAO;
        this.pasajeroDAO = pasajeroDAO;
    }

    @Override
    public void cerrarSesion() {
        SesionUsuario.cerrarSesion();
    }

    @Override
    public boolean validarUsuario(UsuarioDTO usuarioDto) {
       try {
            String contrasena = new String(usuarioDto.getContraseña());

            // Consulta la BD solo por nombre de usuario
            Optional<Usuario> optionalEntidad = usuarioDAO.consultarPorCredenciales(
                    usuarioDto.getUsuario(),
                    contrasena
            );

            if (optionalEntidad.isPresent()) {
                Usuario entidad = optionalEntidad.get();

                // Verificar la contraseña usando BCrypt
                if (!org.base_datos_viajes.util.PasswordUtil.verifyPassword(contrasena, entidad.getContraseña())) {
                    return false;
                }

                UsuarioDTO usuarioLogueado = adaptadorUsuario.toDTO(entidad);

                //Cargar Perfil Conductor
                if (entidad.getConductorId() != null) {
                    Optional<Conductor> optionalConductor = conductorDAO.findById(entidad.getConductorId());
                    if (optionalConductor.isPresent()) {
                         usuarioLogueado.setConductor(adaptadorConductor.toDTO(optionalConductor.get()));
                    }
                }

                //Carga Perfil Pasajero
                if (entidad.getPasajeroId() != null) {
                    Optional<Pasajero> optionalPasajero = pasajeroDAO.findById(entidad.getPasajeroId());
                    if (optionalPasajero.isPresent()) {
                        usuarioLogueado.setPasajero(adaptadorPasajero.toDTO(optionalPasajero.get()));
                    }
                }

                SesionUsuario.iniciarSesion(usuarioLogueado);
                return true;
            }
            return false;
        } catch (DatabaseException e) {
            System.err.println("Error de BD al validar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public UsuarioDTO obtenerUsuarioActivo() {
        return SesionUsuario.obtenerUsuario();
    }
}
