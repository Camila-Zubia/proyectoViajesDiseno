
package presentacion_gestionarSolicitudes;

import Controles.IControlPantallas;
import dto.ReservacionDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author ferch
 */
public class GestionSolicitudes extends javax.swing.JPanel {

    private final IControlPantallas controlPantallas;
    private final String viajeId;
    private List<ReservacionDTO> solicitudes;
    private final javax.swing.JLabel tituloViajeLabel;
    private final javax.swing.JPanel panelContenedor;

    /**
     * Creates new form solicitudesPendientes
     */
    public GestionSolicitudes(IControlPantallas controlPantallas, String viajeId, List<ReservacionDTO> solicitudes) {
        this.controlPantallas = controlPantallas;
        this.viajeId = viajeId;
        this.solicitudes = solicitudes;

        this.tituloViajeLabel = new javax.swing.JLabel();
        this.panelContenedor = new javax.swing.JPanel();

        initComponents();
        jPanel2.setLayout(null);

        tituloViajeLabel.setFont(new java.awt.Font("Segoe UI", 1, 36));
        tituloViajeLabel.setForeground(new java.awt.Color(255, 255, 255));
        tituloViajeLabel.setText("SOLICITUDES PENDIENTES (Viaje: " + viajeId.substring(0, 5) + "...)");

        panelContenedor.setBackground(java.awt.Color.WHITE);
        panelContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tituloViajeLabel.setBounds(20, 30, 850, 50);
        jPanel2.add(tituloViajeLabel);

        panelContenedor.setBounds(20, 100, 800, 380);
        jPanel2.add(panelContenedor);

        int regresarY = jPanel2.getHeight() > 0 ? jPanel2.getHeight() - 50 - 25 : 495;
        regresarBtn.setBounds(10, regresarY, 201, 50);

        mostrarSolicitudes();

        jPanel2.revalidate();
        jPanel2.repaint();
    }

    // Método para dibujar la lista de solicitudes en el panel contenedor
    private void mostrarSolicitudes() {
        panelContenedor.removeAll();

        JPanel panelInterno = new JPanel();
        panelInterno.setBackground(Color.WHITE);
        panelInterno.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        if (solicitudes.isEmpty()) {
            JPanel mensajeWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
            mensajeWrapper.setBackground(Color.WHITE);
            mensajeWrapper.add(new JLabel("No hay solicitudes pendientes para este viaje."));
            panelInterno.add(mensajeWrapper);
        } else {
            for (ReservacionDTO reserva : solicitudes) {
                JPanel panelElemento = crearPanelSolicitud(reserva);
                panelInterno.add(panelElemento);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panelInterno);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 350));
        scrollPane.setBackground(Color.WHITE);

        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(scrollPane, BorderLayout.CENTER);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    /**
     * Crea un panel por solicitud con botones de acción.
     */
    private JPanel crearPanelSolicitud(ReservacionDTO reserva) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setPreferredSize(new Dimension(780, 80));
        panel.setMaximumSize(new Dimension(780, 80));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        panel.setBackground(new Color(255, 255, 255));

        String direccionParada = "ERROR: Sin Parada";

        if (reserva.getParada() != null && reserva.getParada().getDirección() != null) {
            direccionParada = reserva.getParada().getDirección();
        }
        boolean esSolicitudRuta = reserva.getParada() != null && reserva.getParada().getId() == null;

        String tipoSolicitud = esSolicitudRuta ? "RUTA PROPUESTA" : "ESTANDAR";

        JPanel panelElemento = new JPanel(new java.awt.BorderLayout());
        panelElemento.setPreferredSize(new Dimension(780, 50));
        panelElemento.setMaximumSize(new Dimension(780, 50));
        panelElemento.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        panelElemento.setBackground(new Color(245, 245, 245));

        JButton btnInfo = new JButton();
        btnInfo.setFont(new Font("Dialog", Font.BOLD, 14));
        btnInfo.setBackground(Color.WHITE);
        btnInfo.setForeground(Color.BLACK);
        btnInfo.setHorizontalAlignment(SwingConstants.LEFT);
        btnInfo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String infoTexto = String.format("Solicitud (%s) | Pasajero: %s | Parada: %s | Precio: $%.2f",
                tipoSolicitud,
                reserva.getPasajero().getNombre(),
                direccionParada,
                reserva.getPrecioTotal());
        btnInfo.setText("<html><b>" + infoTexto + "</b></html>");

        btnInfo.addActionListener(e -> {
            if (esSolicitudRuta) {
                controlPantallas.mostrarDetalleReservaRuta(reserva);
            } else {
                controlPantallas.mostrarDetalleReservaEstandar(reserva);
            }
        });

        panelElemento.add(btnInfo, BorderLayout.CENTER);
        return panelElemento;

    }

    
    private void recargarSolicitudes() {
        this.solicitudes = controlPantallas.obtenerSolicitudesPendientes(this.viajeId);
        mostrarSolicitudes();
        panelContenedor.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        regresarBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 109, 182));
        jPanel1.setMinimumSize(new java.awt.Dimension(1167, 630));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 640));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 109, 182));

        regresarBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        regresarBtn.setText("Regresar");
        regresarBtn.setBorder(null);
        regresarBtn.addActionListener(this::regresarBtnActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regresarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(683, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(502, Short.MAX_VALUE)
                .addComponent(regresarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 890, 570));
        jPanel2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1069, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarBtnActionPerformed
        controlPantallas.mostrarSeleccionarViajeGestion();
    }//GEN-LAST:event_regresarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton regresarBtn;
    // End of variables declaration//GEN-END:variables
}
