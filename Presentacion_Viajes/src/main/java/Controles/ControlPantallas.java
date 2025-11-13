/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import dto.ConductorDTO;
import dto.UsuarioDTO;
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
import registrarViaje.IRegistrarViaje;
import registrarViaje.RegistrarViaje;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlPantallas {

    private final JFrame frame;
    private final JMenu menu;
    private final IIniciarSesion sesion = new IniciarSesion();
    private final IRegistrarViaje interfaz = new RegistrarViaje();

    public ControlPantallas(JFrame frame, JMenu menu) {
        this.frame = frame;
        this.menu = menu;
    }

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

    public void mostrarMenuVehiculos() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List vehiculos = interfaz.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
    }
    
    public void mostrarInicioSesion(){
        iniciarSesion inicioSesion = new iniciarSesion(this);
        configurarPanel(inicioSesion);
    }

    public void mostrarMenuConductor() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfaz.obtenerViajesPorConductor(usuario.getConductor());
        menu.setEnabled(true);
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
    }

    public void mostrarDatosViaje() {
        datosViaje datosViaje = new datosViaje(this);
        configurarPanel(datosViaje);
    }

    public void mostrarDatosParada() {
        List paradas = interfaz.obtenerParadasTemporales();
        datosParadas datosParadas = new datosParadas(this, paradas);
        configurarPanel(datosParadas);
    }

    public void mostrarSeleccionarPerfil() {
        seleccionarPerfilConductor panel = new seleccionarPerfilConductor(this);
        configurarPanel(panel);
        panel.setVisible(true);
        frame.setTitle("Seleccionar Perfil");
    }

    public void seleccionarVehiculo(dto.VehiculoDTO vehiculo) {
        interfaz.seleccionarVehiculo(vehiculo);
    }

    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora) {
        interfaz.guardarDatosViaje(origen, destino, fecha, hora);
    }

    public void confirmarViaje() {
        interfaz.confirmarViaje();
    }
    
    public void agregarParada(String direccion, double precio){
        interfaz.agregarParada(direccion, precio);
    }
    
    public boolean validarUsuario(UsuarioDTO usuario){
        return sesion.validarUsuario(usuario);
    }
    
    public ConductorDTO nombreConductor(){
        return sesion.obtenerUsuario().getConductor();
    }
    
    public void cerrarSesion(){
        sesion.cerrarSesion();
        menu.setEnabled(false);
        mostrarInicioSesion();
    }
}
