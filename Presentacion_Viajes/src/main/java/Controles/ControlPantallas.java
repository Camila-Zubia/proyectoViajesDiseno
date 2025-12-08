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
import dto.AdeudoDTO;
import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.PasajeroDTO;
import dto.ReservacionDTO;
import dto.RutaFrecuenteDTO;
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
import presentacion.menuAdeudos;
import presentacion.menuPrincipalConductor;
import presentacion.menuVehiculos;
import presentacion.seleccionarPerfilConductor;
import presentacion_SolicitarReservacion.datosReservacion;
import presentacion_SolicitarReservacion.menuPrincipalPasajero;
import presentacion_SolicitarReservacion.seleccionarParada;
import presentacion_cancelarReservacion.cancelarReservacion;
import presentacion_cancelarReservacion.seleccionarReservacion;
import presentacion_crearRutaFrecuente.DatosParadasRuta;
import presentacion_crearRutaFrecuente.DatosRutaFrec;
import presentacion_crearRutaFrecuente.MenuRutasFrecuentes;
import presentacion_crearRutaFrecuente.SeleccionarVehiculoRuta;
import presentacion_registrarVehiculo.datosPropietario;
import presentacion_registrarVehiculo.datosVehiculo;
import presentacion_registrarVehiculo.menuVehiculosConductor;
import registrarVehiculo.FRegistrarVehiculo;
import registrarVehiculo.IRegistrarVehiculo;
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
    private final IRegistrarVehiculo interfazRegistrarVehiculo = new FRegistrarVehiculo();
    
    private boolean perfil;
    private final pagarAdeudo.IPagarAdeudo interfazPagarAdeudo = new pagarAdeudo.PagarAdeudo();

    private ViajeDTO viajeTemporal;

    private ControlPantallas(JFrame frame, JMenu menu) {
        this.frame = frame;
        this.menu = menu;
        perfil = false;
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
    public boolean obtenerPerfil(){
        return perfil;
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
        perfil = true;
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
        perfil = false;
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
    
    @Override
    public String formatearTiempoRestante(Long tiempoSegundos) {
        if (tiempoSegundos == null) {
            return "--:--:--";
        }
        if (tiempoSegundos < 0) {
            return "Iniciado/Expirado";
        }
        long dias = tiempoSegundos / 86400;
        long restoDelDia = tiempoSegundos % 86400;
        long horas = restoDelDia / 3600;
        long minutos = (restoDelDia % 3600) / 60;
        long segundos = restoDelDia % 60;

        if (dias > 0) {
            return String.format("%d días, %02d:%02d:%02d", dias, horas, minutos, segundos);
        } else {
            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        }
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
        DatosParadasRuta datosParadasRutas = new DatosParadasRuta(this, paradasRuta);
        configurarPanel(datosParadasRutas);
    }

    @Override
    public void mostrarMenuRutasFrecuentes() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List RutasFrecuentes = interfazCrearRutaFrecuente.obtenerRutaPorConductor(usuario.getConductor());
        MenuRutasFrecuentes menuRutas = new MenuRutasFrecuentes(this, RutasFrecuentes);
        configurarPanel(menuRutas);
    }

    @Override
    public void mostrarSeleccionarVehiculoRuta() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List vehiculos = interfazRegistrarViaje.obtenerVehiculosDisponibles(usuario.getConductor());
        SeleccionarVehiculoRuta menuVehiculos = new SeleccionarVehiculoRuta(this, vehiculos);
        configurarPanel(menuVehiculos);
    }

    public ViajeDTO getViajeTemporal() {

        return interfazRegistrarViaje.getViajeTemporal();
    }

    @Override
    public void GuardarDatosRutaFrec(String nombre, String origen, String destino, LocalDate fecha, LocalTime hora) {
        interfazCrearRutaFrecuente.GuardarDatosRutaFrec(nombre, origen, destino, fecha, hora);
    }

    @Override
    public void agregarParadaRuta(String direccion, double precio) {

        interfazCrearRutaFrecuente.agregarParada(direccion, precio);
    }

    @Override
    public List<ParadaDTO> obtenerParadasRuta(RutaFrecuenteDTO ruta) {

        return interfazCrearRutaFrecuente.obtenerParadas(ruta);
    }

    @Override
    public List<ParadaDTO> obtenerParadasTempoRuta() {
        return interfazCrearRutaFrecuente.obtenerParadasTemp();
    }

    @Override
    public RutaFrecuenteDTO ConfirmarRuta() {
        return interfazCrearRutaFrecuente.confirmaRuta();
    }

    // Métodos para cancelar viaje
    @Override
    public void mostrarDetallesViaje() {
        ViajeDTO viaje = obtenerViajeTemporal();
        detallesViaje detalles = new detallesViaje(this, viaje);
        configurarPanel(detalles);
    }

    ;

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

    // metodos para registra vehiculo
    //@Override
    @Override
    public void mostrarMenuVehiculosConductor() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List vehiculos = interfazRegistrarViaje.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculosConductor menuVehiculosConductor = new menuVehiculosConductor(this, vehiculos);
        configurarPanel(menuVehiculosConductor);
    }

    //@Override
    @Override
    public void mostrarDatosVehiculo() {
        datosVehiculo datosVehiculo = new datosVehiculo(this);
        configurarPanel(datosVehiculo);
    }

    @Override
    public void mostrarDatosPropietario() {
        datosPropietario datosPropietario = new datosPropietario(this);
        configurarPanel(datosPropietario);
    }

    @Override
    public void guardarDatosVehiculo(String modelo, String placas, String marca, String color, int CantidadPasajeros) {

        interfazRegistrarVehiculo.guardarDatosVehiculo(modelo, placas, marca, color, CantidadPasajeros);

    }

    @Override
    public void guardarDatosPropietario(String nombre, String curp, String rfc, String nss) {

        interfazRegistrarVehiculo.guardarDatosPropietario(nombre, curp, rfc, nss);
    }

    @Override
    public void ConfirmarDatosVehiculoPropietario() {

        interfazRegistrarVehiculo.confirmarRegistroVehiculoPropietario();
    @Override
    public ViajeDTO obtenerDetallesViaje(String idViaje) {
        return interfazPagarAdeudo.obtenerDetallesViaje(idViaje);
    }

    // Métodos para pagar adeudos
    @Override
    public void mostrarMenuAdeudos() {
        List<AdeudoDTO> adeudos = obtenerAdeudosPendientes();
        menuAdeudos menu = new menuAdeudos(this, adeudos);
        configurarPanel(menu);
    }

    @Override
    public List<AdeudoDTO> obtenerAdeudosPendientes() {
        ConductorDTO conductor = nombreConductor();
        if (conductor == null) {
            throw new IllegalStateException("No hay un conductor en sesión");
        }

        return interfazPagarAdeudo.obtenerAdeudosPendientes(conductor.getId());
    }

    @Override
    public void marcarAdeudoComoPagado(String idAdeudo) {
        interfazPagarAdeudo.pagarAdeudo(idAdeudo);
    }

}
