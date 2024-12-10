import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAlmacen extends JPanel {

    private JTextArea historialArea;
    private JButton tecladoButton, ratonButton, ambosButton;

    public PantallaAlmacen() {
        setLayout(new BorderLayout());

        JPanel botonesPanel = new JPanel(new FlowLayout());
        tecladoButton = new JButton("Teclado");
        ratonButton = new JButton("Ratón");
        ambosButton = new JButton("Ambos");

        botonesPanel.add(tecladoButton);
        botonesPanel.add(ratonButton);
        botonesPanel.add(ambosButton);

        historialArea = new JTextArea(10, 20);
        historialArea.setEditable(false);
        historialArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(historialArea);

        add(botonesPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        tecladoButton.addActionListener(new ModoSeleccionadoListener("Teclado"));
        ratonButton.addActionListener(new ModoSeleccionadoListener("Ratón"));
        ambosButton.addActionListener(new ModoSeleccionadoListener("Ambos"));
    }

    public void agregarAlHistorial(String operacion) {
        historialArea.append(operacion + "\n");
    }
    
    private class ModoSeleccionadoListener implements ActionListener {
        private String modo;

        public ModoSeleccionadoListener(String modo) {
            this.modo = modo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            agregarAlHistorial("Modo seleccionado: " + modo);
        }
    }
}
