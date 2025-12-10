package presentacion;

import Controles.IControlPantallas;
import dto.AdeudoDTO;
import dto.ViajeDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class menuAdeudos extends javax.swing.JPanel {

    private final IControlPantallas controlPantallas;
    private static final String URL_BANCO = "http://localhost:5173";

    public menuAdeudos(IControlPantallas controlPantallas, List<AdeudoDTO> adeudos) {
        this.controlPantallas = controlPantallas;

        initComponents();
        mostrarAdeudos(adeudos);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void initComponents() {
        setBackground(new Color(0, 109, 182));
        setMinimumSize(new Dimension(1080, 640));
        setPreferredSize(new Dimension(1080, 638));
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(0, 109, 182));
        panelPrincipal.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Tus adeudos:");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void mostrarAdeudos(List<AdeudoDTO> adeudos) {
        JPanel panelPrincipal = (JPanel) ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER);

        JPanel contenedorAdeudos = new JPanel();
        contenedorAdeudos.setLayout(new BoxLayout(contenedorAdeudos, BoxLayout.Y_AXIS));
        contenedorAdeudos.setBackground(new Color(0, 109, 182));

        List<AdeudoDTO> adeudosPendientes = adeudos.stream()
            .filter(a -> !a.isPagado())
            .toList();

        if (adeudosPendientes.isEmpty()) {
            JLabel mensaje = new JLabel("No tienes adeudos pendientes");
            mensaje.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            mensaje.setForeground(Color.WHITE);
            mensaje.setAlignmentX(CENTER_ALIGNMENT);
            mensaje.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
            contenedorAdeudos.add(mensaje);
        } else {
            for (AdeudoDTO adeudo : adeudosPendientes) {
                JPanel panelAdeudo = crearPanelAdeudo(adeudo);
                contenedorAdeudos.add(panelAdeudo);
                contenedorAdeudos.add(javax.swing.Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(contenedorAdeudos);
        scrollPane.setBackground(new Color(0, 109, 182));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelAdeudo(AdeudoDTO adeudo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(211, 211, 211));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        panel.setPreferredSize(new Dimension(700, 100));

        String textoViaje = "";
        String textoHora = "";
        String textoRuta = "";

        if (adeudo.getIdViaje() != null && !adeudo.getIdViaje().isEmpty()) {
            try {
                ViajeDTO viaje = controlPantallas.obtenerDetallesViaje(adeudo.getIdViaje());
                if (viaje != null) {
                    textoViaje = "Viaje#" + viaje.getId().substring(viaje.getId().length() - 4);
                    textoHora = "Hora:" + viaje.getHora().format(DateTimeFormatter.ofPattern("HH:mm"));
                    textoRuta = "Inicio: " + viaje.getOrigen() + "  Destino: " + viaje.getDestino();
                }
            } catch (Exception e) {
                textoViaje = "Adeudo";
                textoRuta = adeudo.getConcepto();
            }
        } else {
            textoViaje = "Adeudo";
            textoRuta = adeudo.getConcepto();
        }

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(211, 211, 211));

        JLabel labelViaje = new JLabel(textoViaje + "   " + textoHora);
        labelViaje.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel labelRuta = new JLabel(textoRuta);
        labelRuta.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel labelMonto = new JLabel("Monto: $" + adeudo.getMonto());
        labelMonto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelMonto.setForeground(new Color(200, 0, 0));

        panelInfo.add(labelViaje);
        panelInfo.add(javax.swing.Box.createVerticalStrut(5));
        panelInfo.add(labelRuta);
        panelInfo.add(javax.swing.Box.createVerticalStrut(5));
        panelInfo.add(labelMonto);

        JButton btnPagar = new JButton("Pagar");
        btnPagar.setBackground(new Color(220, 53, 69));
        btnPagar.setForeground(Color.WHITE);
        btnPagar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPagar.setFocusPainted(false);
        btnPagar.setBorderPainted(false);
        btnPagar.setPreferredSize(new Dimension(100, 40));
        btnPagar.addActionListener(e -> confirmarYPagar(adeudo));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setBackground(new Color(211, 211, 211));
        panelBoton.add(btnPagar);

        panel.add(panelInfo, BorderLayout.CENTER);
        panel.add(panelBoton, BorderLayout.EAST);

        return panel;
    }

    private void confirmarYPagar(AdeudoDTO adeudo) {
        String mensaje = "Estas seguro que quieres pagar y ser redirigido al banco?";

        int opcion = JOptionPane.showOptionDialog(
            this,
            mensaje,
            "Código pago",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Si", "Salir"},
            "Salir"
        );

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                abrirPaginaBanco(adeudo.getId());

                JOptionPane.showMessageDialog(
                    this,
                    "Serás redirigido al banco para completar el pago.\n\nDespués de pagar, presiona OK para actualizar la lista.",
                    "Redirigiendo al banco",
                    JOptionPane.INFORMATION_MESSAGE
                );

                controlPantallas.mostrarMenuAdeudos();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error al abrir el banco: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void abrirPaginaBanco(String idAdeudo) {
        try {
            String urlConParametros = URL_BANCO + "?id=" + idAdeudo;

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(urlConParametros));
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "No se puede abrir el navegador automáticamente. Por favor visita: " + urlConParametros,
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al abrir el navegador: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
