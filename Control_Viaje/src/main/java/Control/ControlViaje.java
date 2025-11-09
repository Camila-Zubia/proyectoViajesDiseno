/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.UsuarioDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import registrarViaje.IRegistrarViaje;
import registrarViaje.RegistrarViaje;
import utilidades.SesionUsuario;

/**
 *
 * @author David Tirado 00000233047
 */
public class ControlViaje {

    private final IRegistrarViaje fachadaRegistrarViaje;
    private final SesionUsuario sesion;

    // Estado temporal del viaje
    private VehiculoDTO vehiculoSeleccionado;
    private final List<ParadaDTO> paradasTemporales;

    public ControlViaje() {
        this.fachadaRegistrarViaje = new RegistrarViaje();
        this.sesion = SesionUsuario.getInstancia();
        this.paradasTemporales = new ArrayList<>();
    }

    // Gestión de Usuarios y Conductores
    public void iniciarSesionUsuario(String nombre, String contraseña) {
        if (nombre == null || nombre.isEmpty() || contraseña == null || contraseña.isEmpty()) {
            throw new IllegalArgumentException("El usuario o la contraseña no pueden estar vacíos.");
        }
        UsuarioDTO usuario = new UsuarioDTO(nombre, contraseña);
        sesion.iniciarSesionPasajero(usuario);
    }

    public void asignarConductor(String nombre, int calificacion) {
        if (!sesion.haySesionActiva()) {
            throw new IllegalStateException("Debe iniciar sesión primero.");
        }
        ConductorDTO conductor = new ConductorDTO(nombre, calificacion);
        sesion.iniciarSesionConductor(sesion.getUsuario(), conductor);
    }

    // Obtener información de la sesión
    public UsuarioDTO getUsuarioActivo() {
        return sesion.getUsuario();
    }

    public ConductorDTO getConductorActivo() {
        return sesion.getConductor();
    }

    public boolean haySesionActiva() {
        return sesion.haySesionActiva();
    }

    public void cerrarSesion() {
        sesion.cerrarSesion();
    }

    // Gestión de Vehículos
    public List<VehiculoDTO> obtenerVehiculosDisponibles() {
       
        return fachadaRegistrarViaje.obtenerVehiculos(sesion.getConductor());
    }

    public void seleccionarVehiculo(VehiculoDTO vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("Debe seleccionar un vehículo válido.");
        }
        this.vehiculoSeleccionado = vehiculo;
    }

    // Gestion de Paradasd
    public void agregarParada(String direccion, double precio) {
        if (direccion == null || direccion.isEmpty() || precio < 0) {
            throw new IllegalArgumentException("La dirección no puede estar vacía y el precio debe ser positivo.");
        }
        ParadaDTO parada = new ParadaDTO(direccion, precio);
        fachadaRegistrarViaje.crearParada(parada);
        paradasTemporales.add(parada);
    }

    public void agregarListaParadas(List<ParadaDTO> paradas) {
        if (paradas == null || paradas.isEmpty()) {
            return;
        }
        //fachadaRegistrarViaje.agregarAListaParadas(paradas);
        paradasTemporales.addAll(paradas);
    }

    public List<ParadaDTO> obtenerParadasTemporales() {
        return new ArrayList<>(paradasTemporales);
    }

    // Registro del Viaje
    public ViajeDTO registrarViaje(String origen, String destino, double precioBase) {
        if (origen == null || origen.isEmpty() || destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("El origen y destino son obligatorios.");
        }

        if (vehiculoSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un vehículo antes de registrar el viaje.");
        }

       // if (fachadaRegistrarViaje.validarNoExiste()) {
        //    throw new IllegalStateException("Ya existe un viaje con los mismos datos.");
        //}

        // Calculo del precio total
        double precioTotal = precioBase;
        for (ParadaDTO parada : paradasTemporales) {
            precioTotal += parada.getPrecio();
        }

        ViajeDTO viaje = new ViajeDTO(
                origen,
                destino,
                new Date(),
                LocalTime.now(),
                precioTotal,
                new ArrayList<>(paradasTemporales)
        );

        fachadaRegistrarViaje.crearViaje(viaje);
        paradasTemporales.clear();

        return viaje;
    }
}
