/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

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
    }
    
    public void configurarPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenuVehiculos() {
        List vehiculos = controlViaje.obtenerVehiculosDisponibles();
        menuVehiculos menuVehiculos = new menuVehiculos(this, vehiculos);
        configurarPanel(menuVehiculos);
    }

    public void mostrarMenuConductor() {
        //se debe conseguir el nombre del conductor
        //List viajes = controlViaje.obtenerViajesPorConductor(nombreConductor);
        menuPrincipalConductor menuConductor = new menuPrincipalConductor(this);
        configurarPanel(menuConductor);
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
    
    public void mostrarMenuPrincipalPrincipal(JFrame frame){
        menuPrincipal frm = new menuPrincipal();
        frm.setVisible(true);
        frame.dispose();
    }
}
