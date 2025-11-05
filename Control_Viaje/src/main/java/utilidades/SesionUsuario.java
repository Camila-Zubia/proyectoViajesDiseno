/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import dto.ConductorDTO;
import dto.UsuarioDTO;

/**
 * Clase Singleton para gestionar la sesión del usuario en la aplicación.
 * Mantiene la información del usuario y conductor activos durante toda la sesión.
 *
 * @author Alex Nieblas 252865
 */
public class SesionUsuario {
    private static SesionUsuario instancia;

    private UsuarioDTO usuarioActual;
    private ConductorDTO conductorActual;
    private TipoPerfil perfilActivo;

    /**
     * Enum para identificar el tipo de perfil activo
     */
    public enum TipoPerfil {
        CONDUCTOR,
        PASAJERO,
        NO_DEFINIDO
    }

    /**
     * Constructor privado para evitar instanciación externa.
     */
    private SesionUsuario() {
        this.perfilActivo = TipoPerfil.NO_DEFINIDO;
    }

    /**
     * Obtiene la instancia única del singleton.
     *
     * @return La instancia única de SesionUsuario
     */
    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    /**
     * Inicia sesión para un usuario con perfil de pasajero.
     *
     * @param usuario El usuario que inicia sesión
     */
    public void iniciarSesionPasajero(UsuarioDTO usuario) {
        this.usuarioActual = usuario;
        this.conductorActual = null;
        this.perfilActivo = TipoPerfil.PASAJERO;
    }

    /**
     * Inicia sesión para un usuario con perfil de conductor.
     *
     * @param usuario El usuario que inicia sesión
     * @param conductor La información del conductor
     */
    public void iniciarSesionConductor(UsuarioDTO usuario, ConductorDTO conductor) {
        this.usuarioActual = usuario;
        this.conductorActual = conductor;
        this.perfilActivo = TipoPerfil.CONDUCTOR;
    }

    /**
     * Obtiene el usuario actual de la sesión.
     *
     * @return El usuario actual, o null si no hay sesión activa
     */
    public UsuarioDTO getUsuario() {
        return usuarioActual;
    }

    /**
     * Obtiene el conductor actual de la sesión.
     *
     * @return El conductor actual, o null si no es un perfil de conductor
     */
    public ConductorDTO getConductor() {
        return conductorActual;
    }

    /**
     * Obtiene el tipo de perfil activo en la sesión actual.
     *
     * @return El tipo de perfil activo
     */
    public TipoPerfil getPerfilActivo() {
        return perfilActivo;
    }

    /**
     * Cierra la sesión actual, limpiando todos los datos.
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
        this.conductorActual = null;
        this.perfilActivo = TipoPerfil.NO_DEFINIDO;
    }

    /**
     * Verifica si hay una sesión activa.
     *
     * @return true si hay una sesión activa, false en caso contrario
     */
    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    /**
     * Verifica si el perfil activo es de conductor.
     *
     * @return true si el perfil activo es CONDUCTOR, false en caso contrario
     */
    public boolean esConductor() {
        return perfilActivo == TipoPerfil.CONDUCTOR;
    }

    /**
     * Verifica si el perfil activo es de pasajero.
     *
     * @return true si el perfil activo es PASAJERO, false en caso contrario
     */
    public boolean esPasajero() {
        return perfilActivo == TipoPerfil.PASAJERO;
    }

    /**
     * Obtiene el nombre de usuario de la sesión actual.
     *
     * @return El nombre de usuario, o null si no hay sesión activa
     */
    public String getNombreUsuario() {
        return usuarioActual != null ? usuarioActual.getUsuario() : null;
    }

    /**
     * Actualiza la información del conductor en la sesión actual.
     *
     * @param conductor La nueva información del conductor
     */
    public void actualizarConductor(ConductorDTO conductor) {
        if (esConductor()) {
            this.conductorActual = conductor;
        }
    }

    @Override
    public String toString() {
        if (!haySesionActiva()) {
            return "No hay sesión activa";
        }

        if (esConductor() && conductorActual != null) {
            return String.format("Sesión activa: %s (Conductor: %s, Calificación: %d estrellas)",
                    usuarioActual.getUsuario(),
                    conductorActual.getNombre(),
                    conductorActual.getCalificación());
        } else {
            return String.format("Sesión activa: %s (Pasajero)",
                    usuarioActual.getUsuario());
        }
    }
}
