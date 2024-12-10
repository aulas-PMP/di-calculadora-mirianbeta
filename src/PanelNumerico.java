import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelNumerico extends JPanel {

    private Pantalla pantalla;
    private PantallaAlmacen pantallaAlmacen;
    private StringBuilder operacionActual = new StringBuilder();

    public PanelNumerico(Pantalla pantalla, PantallaAlmacen pantallaAlmacen) {
        this.pantalla = pantalla;
        this.pantallaAlmacen = pantallaAlmacen;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;

        for (int number = 1; number <= 9; number++) {
            JButton boton = new JButton(String.valueOf(number));
            boton.addActionListener(e -> agregarNumero(boton.getText()));
            add(boton);
        }

        JButton boton0 = new JButton("0");
        boton0.addActionListener(e -> agregarNumero(boton0.getText()));
        add(boton0);
        JButton botonComa = new JButton(",");
        botonComa.addActionListener(e -> agregarNumero(botonComa.getText()));
        add(botonComa);
    }

    private void agregarNumero(String numero) {
        String operacionActual = pantalla.getOperacionActual();
        if (operacionActual.equals("0")) {
            operacionActual = ""; // Si la operación actual es "0", la reemplazamos
        }
        pantalla.actualizarOperacion(operacionActual + numero);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ajustarDistribucion(getHeight());
    }

    private void ajustarDistribucion(int height) {
        GridBagLayout layout = (GridBagLayout) getLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;

        int totalHeight = height - (4 * 4);
        int buttonHeight = totalHeight / 4;

        int number = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weighty = buttonHeight / (double) height;
                JButton boton = (JButton) getComponent(number - 1);
                boton.setFont(new Font("Arial", Font.PLAIN, 30));
                layout.setConstraints(boton, gbc);
                number++;
            }
        }

        JButton boton0 = (JButton) getComponent(9);
        boton0.setFont(new Font("Arial", Font.PLAIN, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        layout.setConstraints(boton0, gbc);

        JButton botonComa = (JButton) getComponent(10);
        botonComa.setFont(new Font("Arial", Font.PLAIN, 30));
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        layout.setConstraints(botonComa, gbc);

        revalidate();
        repaint();
    }

}