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
    private UsuarioDTO usuario = ControlSesion.getUsuarioActual();

    public ControlPantallas(JFrame frame) {
        this.frame = frame;
    }
    
    public void configurarPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenuVehiculos() {
        List vehiculos = controlViaje.obtenerVehiculosDisponibles(usuario.getConductor());
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
    }

    public void mostrarMenuConductor() {
        List viajes = controlViaje.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
    }
    
    public void mostrarMenuPrincipalConductor(JFrame frame) {
        List viajes = controlViaje.obtenerViajesPorConductor(usuario.getConductor());
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this, viajes);
        configurarPanel(menuConductor);
        menuPrincipal frm = new menuPrincipal();
        frm.setVisible(true);
        frame.dispose();
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
    
    public void mostrarSeleccionarPerfil(JFrame frame){
        seleccionarPerfilConductor frm = new seleccionarPerfilConductor();
        frm.setVisible(true);
        frame.dispose();
    }
    
}
