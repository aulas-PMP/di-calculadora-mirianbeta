import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase de una calculadora gráfica con una interfaz
 * de usuario que permite interactuar tanto con el teclado como con el ratón.
 * Gestiona la disposición de los paneles y la lógica
 * de las operaciones. Los modos de entrada se pueden alternar entre teclado y
 * ratón.
 */
public class CalculadoraNew extends JFrame {

    private PantallaAlmacen pantallaAlmacen;
    private PanelNumerico panelNumerico;
    private PanelOperador panelOperador;
    private Pantalla pantalla;

    /**
     * Constructor de la clase. Inicializa la interfaz gráfica de la calculadora,
     * configura la disposición de los paneles, y establece el tamaño y
     * comportamiento de la ventana.
     */
    public CalculadoraNew() {
        setTitle("Calculadora de Miriam");
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = miPantalla.getScreenSize();
        int anchoPantalla = tamanoPantalla.width;

        setSize(anchoPantalla / 2, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int preferredWidth = anchoPantalla / 2;
        int preferredHeight = 600;
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                    setSize(new Dimension(preferredWidth, preferredHeight));
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                setLocationRelativeTo(null);
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        CalculadoraLogica calculadoraLogica = new CalculadoraLogica();

        pantalla = new Pantalla();
        pantallaAlmacen = new PantallaAlmacen(pantalla);

        panelNumerico = new PanelNumerico(pantalla, pantallaAlmacen);
        panelOperador = new PanelOperador(calculadoraLogica, pantalla, pantallaAlmacen);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        add(pantallaAlmacen, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.25;
        add(pantalla, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.75;
        add(panelNumerico, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.75;
        add(panelOperador, gbc);

        setFocusable(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                requestFocusInWindow();
            }
        });

        agregarMouseListenerRecursivo(panelNumerico);
        agregarMouseListenerRecursivo(panelOperador);

        pantallaAlmacen.addChangeListener(this::actualizarListenersSegunModo);
        actualizarListenersSegunModo();
    }

    /**
     * Actualiza los eventos según el modo de entrada seleccionado (teclado o
     * ratón).
     * Bloquea o desbloquea los eventos de los paneles numérico y de operadores
     * dependiendo del modo.
     */
    private void actualizarListenersSegunModo() {
        boolean modoTeclado = pantallaAlmacen.isModoTeclado();
        boolean modoRaton = pantallaAlmacen.isModoRaton();

        if (modoTeclado && !modoRaton) {
            bloquearEventos(panelNumerico);
            bloquearEventos(panelOperador);
        } else if (!modoTeclado && modoRaton) {
            desbloquearEventos(panelNumerico);
            desbloquearEventos(panelOperador);
        } else {
            desbloquearEventos(panelNumerico);
            desbloquearEventos(panelOperador);
        }
    }

    /**
     * Este listener maneja las interacciones con los botones mediante el ratón.
     * 
     * @param componente el componente al cual se le agregará el listener.
     */
    private void agregarMouseListenerRecursivo(Component componente) {
        componente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!pantallaAlmacen.isModoTeclado()) {
                    if (e.getSource() instanceof JButton) {
                        JButton boton = (JButton) e.getSource();
                        pantallaAlmacen.manejarEntradaRaton(
                                new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, boton.getText()));
                    }
                }
            }
        });

        if (componente instanceof Container) {
            for (Component hijo : ((Container) componente).getComponents()) {
                agregarMouseListenerRecursivo(hijo);
            }
        }
    }

    /**
     * Bloquea todos los eventos de los componentes dentro de un contenedor,
     * deshabilitándolos.
     * 
     * @param container el contenedor cuyo contenido debe ser deshabilitado.
     */
    private void bloquearEventos(Container container) {
        for (Component componente : container.getComponents()) {
            componente.setEnabled(false);
        }
    }

    /**
     * Desbloquea todos los eventos de los componentes dentro de un contenedor,
     * habilitándolos.
     * 
     * @param container el contenedor cuyo contenido debe ser habilitado.
     */
    private void desbloquearEventos(Container container) {
        for (Component componente : container.getComponents()) {
            componente.setEnabled(true);
        }
    }

    /**
     * Método principal para ejecutar la aplicación de la calculadora.
     * 
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraNew().setVisible(true);
        });
    }
}
