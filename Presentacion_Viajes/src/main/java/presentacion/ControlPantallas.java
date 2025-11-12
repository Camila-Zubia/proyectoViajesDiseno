/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import dto.UsuarioDTO;
import iniciarSesion.IIniciarSesion;
import iniciarSesion.IniciarSesion;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import registrarViaje.IRegistrarViaje;
import registrarViaje.RegistrarViaje;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlPantallas {

    private final JFrame frame;
    private final IIniciarSesion sesion = new IniciarSesion();
    private final IRegistrarViaje interfaz = new RegistrarViaje();

    public ControlPantallas(JFrame frame) {
        this.frame = frame;
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
    
    public void configurarPanel(JFrame frame, JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenuVehiculos() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List vehiculos = interfaz.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
        frame.setVisible(true);
    }

    public void mostrarMenuConductor() {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfaz.obtenerViajesPorConductor(usuario.getConductor());

        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
    }

    public void mostrarMenuConductor(JFrame frame) {
        UsuarioDTO usuario = sesion.obtenerUsuario();
        List viajes = interfaz.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(frame, menuConductor);
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

    public void mostrarMenuPrincipal(JFrame frame) {
        menuPrincipal frm = new menuPrincipal();
        frm.setVisible(true);
        frame.dispose();
        mostrarMenuConductor(frm);
    }

    public void seleccionarVehiculo(dto.VehiculoDTO vehiculo) {
        interfaz.seleccionarVehiculo(vehiculo);
    }

    public void guardarDatosViaje(String origen, String destino, double precioBase) {
        interfaz.guardarDatosViaje(origen, destino, precioBase);
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
}
