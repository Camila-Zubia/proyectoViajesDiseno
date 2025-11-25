/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import dto.ConductorDTO;
import dto.UsuarioDTO;
import dto.ViajeDTO;
import iniciarSesion.IIniciarSesion;
import iniciarSesion.IniciarSesion;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import presentacion.datosParadas;
import presentacion.datosViaje;
import presentacion.iniciarSesion;
import presentacion.menuPrincipalConductor;
import presentacion.menuVehiculos;
import presentacion.seleccionarPerfilConductor;
import presentacion_SolicitarReservacion.menuPrincipalPasajero;
import registrarViaje.IRegistrarViaje;
import registrarViaje.RegistrarViaje;

/**
 *
 * @author Camila Zubia 00000244825
 */
 public class ControlPantallas implements IControlPantallas{

    private static ControlPantallas instancia;
    private final JFrame frame;
    private final JMenu menu;
    private final IIniciarSesion sesion = new IniciarSesion();
    private final IRegistrarViaje interfaz = new RegistrarViaje();
    private ViajeDTO viajeTemporal;

    private ControlPantallas(JFrame frame, JMenu menu) {
        this.frame = frame;
        this.menu = menu;
        this.viajeTemporal = new ViajeDTO();
    }
    
    public static ControlPantallas getInstancia(JFrame frame, JMenu menu){
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
        List vehiculos = interfaz.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
    }
    
    @Override
    public void mostrarInicioSesion(){
        iniciarSesion inicioSesion = new iniciarSesion(this);
        configurarPanel(inicioSesion);
    }

    @Override
    public void mostrarMenuConductor() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfaz.obtenerViajesPorConductor(usuario.getConductor());
        menu.setEnabled(true);
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
        List paradas = interfaz.obtenerParadasTemporales();
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
        interfaz.seleccionarVehiculo(vehiculo);
    }

    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora) {
        viajeTemporal.setOrigen(origen);
        viajeTemporal.setDestino(destino);
        viajeTemporal.setFecha(fecha);
        viajeTemporal.setHora(hora);
    }

    @Override
    public void confirmarViaje() {
        interfaz.confirmarViaje(viajeTemporal);
        // Reiniciar el viaje temporal para el próximo viaje
        viajeTemporal = new ViajeDTO();
    }
    
    @Override
    public void agregarParada(String direccion, double precio){
        interfaz.agregarParada(direccion, precio);
    }
    
    @Override
    public boolean validarUsuario(UsuarioDTO usuario){
        return sesion.validarUsuario(usuario);
    }
    
    @Override
    public ConductorDTO nombreConductor(){
        return sesion.obtenerUsuario().getConductor();
    }
    
    @Override
    public void cerrarSesion(){
        sesion.cerrarSesion();
        menu.setEnabled(false);
        mostrarInicioSesion();
    }
    
    
   //Estos son los metodos del subsitema "Solicitar Reservacion"
    public void mostrarMenuPasajero() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfaz.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalPasajero menuPasajero = new menuPrincipalPasajero(this, viajes);
        configurarPanel(menuPasajero);
    }
    
    
}
