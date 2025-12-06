/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import org.base_datos_viajes.initializer.InicializadorDatosPrueba;
import cancelarReservacion.CancelarReservacion;
import cancelarReservacion.ICancelarReservacion;
import crearRutaFrecuente.FCrearRutaFrecuente;
import crearRutaFrecuente.ICrearRutaFrecuente;
import cancelarViaje.CancelarViaje;
import cancelarViaje.ICancelarViaje;
import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.PasajeroDTO;
import dto.ReservacionDTO;
import dto.UsuarioDTO;
import dto.ViajeDTO;
import editarViaje.EditarViaje;
import editarViaje.IEditarViaje;
import iniciarSesion.IIniciarSesion;
import iniciarSesion.IniciarSesion;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import presentacion.datosParadas;
import presentacion.datosViaje;
import presentacion.detallesViaje;
import presentacion.iniciarSesion;
import presentacion.menuPrincipalConductor;
import presentacion.menuVehiculos;
import presentacion.seleccionarPerfilConductor;
import presentacion_SolicitarReservacion.datosReservacion;
import presentacion_SolicitarReservacion.menuPrincipalPasajero;
import presentacion_SolicitarReservacion.seleccionarParada;
import presentacion_cancelarReservacion.cancelarReservacion;
import presentacion_cancelarReservacion.seleccionarReservacion;
import presentacion_crearRutaFrecuente.DatosParadas;
import presentacion_crearRutaFrecuente.DatosRutaFrec;
import presentacion_crearRutaFrecuente.MenuRutasFrecuentes;
import registrarViaje.IRegistrarViaje;
import registrarViaje.RegistrarViaje;
import solicitarReservacion.ISolicitarReservacion;
import solicitarReservacion.SolicitarReservacion;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlPantallas implements IControlPantallas {

    private static ControlPantallas instancia;
    private final JFrame frame;
    private final JMenu menu;
    private final IIniciarSesion sesion = new IniciarSesion();
    private final IRegistrarViaje interfazRegistrarViaje = new RegistrarViaje();
    private final ISolicitarReservacion interfazSolicitarReservacion = new SolicitarReservacion();
    private final ICancelarReservacion interfazCancelarReservacion = new CancelarReservacion();
    private final ICrearRutaFrecuente interfazCrearRutaFrecuente = new FCrearRutaFrecuente();
    private final ICancelarViaje interfazCancelarViaje = new CancelarViaje();
    private final IEditarViaje interfazEditarViaje = new EditarViaje();

    private ViajeDTO viajeTemporal;

    private ControlPantallas(JFrame frame, JMenu menu) {
        this.frame = frame;
        this.menu = menu;

        InicializadorDatosPrueba.inicializarSiEsNecesario();
    }

    public static ControlPantallas getInstancia(JFrame frame, JMenu menu) {
        if (instancia == null) {
            instancia = new ControlPantallas(frame, menu);
        }
        return instancia;
    }

    @Override
    public void configurarPanel(JPanel panel) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.setVisible(true);
        panel.revalidate();
        panel.repaint();

        frame.revalidate();
        frame.repaint();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarMenuVehiculos() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List vehiculos = interfazRegistrarViaje.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
    }

    @Override
    public void mostrarInicioSesion() {
        iniciarSesion inicioSesion = new iniciarSesion(this);
        configurarPanel(inicioSesion);
    }

    @Override
    public void mostrarMenuConductor() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfazRegistrarViaje.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
    }

    @Override
    public void mostrarDatosViaje() {
        datosViaje datosViaje = new datosViaje(this);
        configurarPanel(datosViaje);
    }

    @Override
    public void mostrarDatosParada() {
        List paradas = interfazRegistrarViaje.obtenerParadasTemporales();
        datosParadas datosParadas = new datosParadas(this, paradas);
        configurarPanel(datosParadas);
    }

    @Override
    public void mostrarSeleccionarPerfil() {
        menu.setEnabled(true);
        seleccionarPerfilConductor panel = new seleccionarPerfilConductor(this);
        configurarPanel(panel);
        panel.setVisible(true);
        frame.setTitle("Seleccionar Perfil");
    }

    @Override
    public void seleccionarVehiculo(dto.VehiculoDTO vehiculo) {
        interfazRegistrarViaje.seleccionarVehiculo(vehiculo);
    }

    @Override
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora, double precioBase) {
        interfazRegistrarViaje.guardarDatosViaje(origen, destino, fecha, hora, precioBase);
    }

    @Override
    public void confirmarViaje() {
        interfazRegistrarViaje.confirmarViaje();
    }

    @Override
    public void agregarParada(String direccion, double precio) {
        interfazRegistrarViaje.agregarParada(direccion, precio);
    }

    @Override
    public boolean validarUsuario(UsuarioDTO usuario) {
        return sesion.validarUsuario(usuario);
    }

    @Override
    public ConductorDTO nombreConductor() {
        return sesion.obtenerUsuario().getConductor();
    }

    @Override
    public void cerrarSesion() {
        sesion.cerrarSesion();
        menu.setEnabled(false);
        mostrarInicioSesion();
    }

    //Estos son los metodos del subsitema "Solicitar Reservacion"
    @Override
    public void mostrarMenuPasajero() {
        List viajes = interfazSolicitarReservacion.obtenerViajesDisponibles();
        menuPrincipalPasajero menuPasajero = new menuPrincipalPasajero(this, viajes);
        configurarPanel(menuPasajero);
    }

    @Override
    public void mostrarParadasViaje() {
        List paradas = interfazSolicitarReservacion.obtenerParadas();
        seleccionarParada menuParadas = new seleccionarParada(this, paradas);
        configurarPanel(menuParadas);
    }

    @Override
    public void mostrarDatosReservacion() {
        ReservacionDTO reservacion = interfazSolicitarReservacion.obtenerReservacionTemporal();
        datosReservacion datos = new datosReservacion(this, reservacion);
        configurarPanel(datos);
    }

    @Override
    public List<ViajeDTO> obtenerViajes() {
        return interfazSolicitarReservacion.obtenerViajesDisponibles();
    }

    @Override
    public List<ParadaDTO> obtenerParadas() {
        return interfazSolicitarReservacion.obtenerParadas();
    }

    @Override
    public void seleccionarViaje(ViajeDTO viaje) {
        interfazSolicitarReservacion.seleccionarViaje(viaje);
        //metodo de editarViaje para inicializar el DTO para su edicion
        interfazEditarViaje.setViajeTemporal(viaje);
        this.viajeTemporal = viaje;
    }

    @Override
    public void seleccionarParada(ParadaDTO parada) {
        interfazSolicitarReservacion.seleccionarParada(parada);
    }

    @Override
    public void solicitarParada(String direccion) {
        interfazSolicitarReservacion.solicitarParada(direccion);
    }

    @Override
    public ReservacionDTO confirmarReservacion() {
        return interfazSolicitarReservacion.confirmarReservacion();
    }

    @Override
    public ReservacionDTO obtenerReservacionTemporal() {
        return interfazSolicitarReservacion.obtenerReservacionTemporal();
    }

    @Override
    public PasajeroDTO nombrePasajero() {
        return sesion.obtenerUsuario().getPasajero();
    }

    //metodos subsistema "CancelarReservacion"
    @Override
    public void mostrarReservaciones() {
        List reservaciones = interfazCancelarReservacion.obtenerReservacionesDisponibles();
        seleccionarReservacion menuReservaciones = new seleccionarReservacion(this, reservaciones);
        configurarPanel(menuReservaciones);
    }

    @Override
    public void mostrarCancelarReservacion() {
        ReservacionDTO reservacion = interfazCancelarReservacion.obtenerReservacion();
        cancelarReservacion cancelar = new cancelarReservacion(this, reservacion);
        configurarPanel(cancelar);
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesDisponibles() {
        return interfazCancelarReservacion.obtenerReservacionesDisponibles();
    }

    @Override
    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion) {
        return interfazCancelarReservacion.seleccionarReservacion(reservacion);
    }

    @Override
    public ReservacionDTO confirmarCancelacion() {
        return interfazCancelarReservacion.confirmarCancelacion();
    }

    @Override
    public ReservacionDTO obtenerReservacion() {
        return interfazCancelarReservacion.obtenerReservacion();
    }

    //metodos subsistema crear ruta frecuente
    @Override
    public void mostrarDatosRutasFrecuente() {

        DatosRutaFrec datosRuta = new DatosRutaFrec(this);
        configurarPanel(datosRuta);
    }

    @Override
    public void mostrarParadasRuta() {
        List paradasRuta = interfazCrearRutaFrecuente.obtenerParadasTemp();
        DatosParadas datosParadasRutas = new DatosParadas(this, paradasRuta);
        configurarPanel(datosParadasRutas);
    }

    //@Override
    public void mostrarMenuRutasFrecuentes() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List RutasFrecuentes = interfazCrearRutaFrecuente.obtenerRutaPorConductor(usuario.getConductor());
        MenuRutasFrecuentes menuRutas = new MenuRutasFrecuentes(this, RutasFrecuentes);
        configurarPanel(menuRutas);
    }
    // Métodos para cancelar viaje
    @Override
    public void mostrarDetallesViaje() {
        ViajeDTO viaje = obtenerViajeTemporal();
        detallesViaje detalles = new detallesViaje(this, viaje);
        configurarPanel(detalles);
    };

    @Override
    public ViajeDTO obtenerViajeTemporal() {
        return viajeTemporal;
    }

    @Override
    public void confirmarCancelacionViaje() {
        try {
            ViajeDTO viaje = obtenerViajeTemporal();

            if (viaje == null || viaje.getId() == null) {
                throw new IllegalStateException("No hay un viaje seleccionado para cancelar");
            }

            boolean cancelado = interfazCancelarViaje.cancelarViaje(viaje.getId());

            if (!cancelado) {
                throw new RuntimeException("No se pudo cancelar el viaje");
            }

            viajeTemporal = null;

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al cancelar viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public int obtenerAdeudoPendiente(String idViaje) {
        return interfazCancelarViaje.obtenerAdeudoPendiente(idViaje);
    }

    //metodos del subsitema editarViaje
    @Override
    public void mostrarEditarViaje() {
        ViajeDTO viaje = obtenerViajeParaEdicion();
        presentacion_editarViaje.editarViaje panel = new presentacion_editarViaje.editarViaje(this);
        configurarPanel(panel);
    }

    @Override
    public void mostrarEditarParadas() {
       ViajeDTO viaje = obtenerViajeParaEdicion();
        presentacion_editarViaje.editarParada panel = new presentacion_editarViaje.editarParada(this);
        configurarPanel(panel);
    }

    @Override
    public void mostrarAgregarParada() {
        presentacion_editarViaje.agregarParada panel = new presentacion_editarViaje.agregarParada(this);
        configurarPanel(panel);
    }

    @Override
    public ViajeDTO obtenerViajeParaEdicion() {
        return interfazEditarViaje.obtenerViajeTemporal();
    }

    @Override
    public void actualizarParadasViaje(List<ParadaDTO> paradas) {
        // Actualizamos la lista de paradas en el DTO temporal en la Fachada
           interfazEditarViaje.guardarParadasTemporales(paradas);
        }
    

    @Override
    public void agregarParadaEnEdicionTemporal(String direccion, double precio) {
        ViajeDTO viaje = interfazEditarViaje.obtenerViajeTemporal();
        if (viaje != null) {
             ParadaDTO nuevaParada = new ParadaDTO(direccion, precio);
             List<ParadaDTO> paradas = viaje.getParadas() != null ? viaje.getParadas() : new ArrayList<>();
             paradas.add(nuevaParada);
             interfazEditarViaje.guardarParadasTemporales(paradas);
        }
    }

    @Override
    public void guardarCambiosViaje(ViajeDTO viajeModificado) {
        interfazEditarViaje.guardarDatosGenerales(viajeModificado);
        }

    @Override
    public ViajeDTO confirmarEdicion() {
        return interfazEditarViaje.confirmarEdicion();
    }

    @Override
    public boolean validarEdicionSegura() {
         return interfazEditarViaje.validarEdicionSegura();
    }
    }
