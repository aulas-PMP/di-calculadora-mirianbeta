import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * PanelNumerico es una clase que representa el panel de entrada numérica de la
 * calculadora.
 * Este panel contiene los botones del 0 al 9, así como el botón para agregar
 * una coma (",").
 * Los botones están organizados en una cuadrícula y permiten ingresar números
 * en la pantalla de la calculadora.
 * También soporta la entrada desde el teclado.
 */
public class PanelNumerico extends JPanel {

    private Pantalla pantalla;
    private PantallaAlmacen pantallaAlmacen;
    private StringBuilder operacionActual = new StringBuilder();

    /**
     * Constructor que inicializa el panel numérico con los botones correspondientes
     * y los agrega a la interfaz gráfica.
     * 
     * @param pantalla        la pantalla de la calculadora que muestra las
     *                        operaciones y resultados
     * @param pantallaAlmacen el panel que almacena el historial de operaciones
     *                        realizadas
     */
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

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_0:
                        agregarNumero("0");
                        break;
                    case KeyEvent.VK_1:
                        agregarNumero("1");
                        break;
                    case KeyEvent.VK_2:
                        agregarNumero("2");
                        break;
                    case KeyEvent.VK_3:
                        agregarNumero("3");
                        break;
                    case KeyEvent.VK_4:
                        agregarNumero("4");
                        break;
                    case KeyEvent.VK_5:
                        agregarNumero("5");
                        break;
                    case KeyEvent.VK_6:
                        agregarNumero("6");
                        break;
                    case KeyEvent.VK_7:
                        agregarNumero("7");
                        break;
                    case KeyEvent.VK_8:
                        agregarNumero("8");
                        break;
                    case KeyEvent.VK_9:
                        agregarNumero("9");
                        break;
                    case KeyEvent.VK_DECIMAL: 
                        agregarNumero(",");
                        break;
                    default:
                        break;
                }
            }
        });
        setFocusable(true); 
    }

    /**
     * Agrega el número o coma a la operación actual en la pantalla.
     * Si la operación actual es "0", se reemplaza por el número o coma ingresado.
     *
     * @param numero el número o coma que se agrega a la operación actual
     */
    private void agregarNumero(String numero) {
        String operacionActual = pantalla.getOperacionActual();
        if (operacionActual.equals("0")) {
            operacionActual = ""; // Si la operación actual es "0", la reemplazamos
        }
        pantalla.actualizarOperacion(operacionActual + numero);
    }

    /**
     * Dibuja el panel, ajustando la distribución de los botones en función de la
     * altura del panel.
     *
     * @param g el objeto Graphics utilizado para pintar el panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ajustarDistribucion(getHeight());
    }

    /**
     * Ajusta la distribución de los botones en la cuadrícula según la altura del
     * panel.
     *
     * @param height la altura del panel para calcular la distribución de los
     *               botones
     */
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
