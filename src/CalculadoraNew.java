import javax.swing.*;
import java.awt.*;

public class CalculadoraNew extends JFrame {

    public CalculadoraNew() {
        setTitle("Calculadora de Mirian");
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = miPantalla.getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        setSize(anchoPantalla / 2, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar el Layout principal de la ventana
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Crear los paneles secundarios
        PantallaAlmacen pantallaAlmacen = new PantallaAlmacen();
        Pantalla pantalla = new Pantalla();
        PanelNumerico panelNumerico = new PanelNumerico();
        PanelOperador panelOperador = new PanelOperador();

        // Configurar constraints para PantallaAlmacen
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; 
        gbc.weightx = 0.5; 
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pantallaAlmacen, gbc);

        // Configurar constraints para Pantalla
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5; 
        gbc.weighty = 0.25; 
        gbc.fill = GridBagConstraints.BOTH;
        add(pantalla, gbc);

        // Configurar constraints para PanelNumerico
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        gbc.weightx = 0.3; 
        gbc.weighty = 0.75; 
        gbc.fill = GridBagConstraints.BOTH;
        add(panelNumerico, gbc);

        // Configurar constraints para PanelOperador
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2; 
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelOperador, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraNew calculadora = new CalculadoraNew();
            calculadora.setVisible(true);
        });
    }
}
