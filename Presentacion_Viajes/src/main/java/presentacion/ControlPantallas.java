/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import dto.UsuarioDTO;
import iniciarSesion.ControlSesion;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import registrarViaje.ControlViaje;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlPantallas {

    private final JFrame frame;
    private ControlViaje controlViaje = new ControlViaje();

    public ControlPantallas(JFrame frame) {
        this.frame = frame;
        
        System.out.println("titulo:" + frame.getTitle());
        System.out.println("Frame:" + frame.hashCode());
        
        
    }

    public void configurarPanel(JPanel panel) {

        System.out.println("Frame actual: " + frame.hashCode());
        System.out.println("Frame title: " + frame.getTitle());

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

    public ControlViaje getControlViaje() {
        return controlViaje;
    }
    
    
    public void configurarPanel(JFrame frame, JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenuVehiculos() {
        
            UsuarioDTO usuario = ControlSesion.getUsuarioActual();

            List vehiculos = controlViaje.obtenerVehiculosDisponibles(usuario.getConductor());

            menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);

            configurarPanel(menuVehiculos);

            frame.setVisible(true);

        
    }

    public void mostrarMenuConductor() {
        UsuarioDTO usuario = ControlSesion.getUsuarioActual();
        List viajes = controlViaje.obtenerViajesPorConductor(usuario.getConductor());

        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
    }

    public void mostrarMenuConductor(JFrame frame) {
        UsuarioDTO usuario = ControlSesion.getUsuarioActual();
        List viajes = controlViaje.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(frame, menuConductor);
    }

    public void mostrarDatosViaje() {
        datosViaje datosViaje = new datosViaje(this);
        configurarPanel(datosViaje);
    }

    public void mostrarDatosParada() {
        List paradas = controlViaje.obtenerParadasTemporales();
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
        controlViaje.seleccionarVehiculo(vehiculo);
    }
}
