import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase PantallaAlmacen es un componente de la interfaz gráfica que permite
 * seleccionar y gestionar los modos de entrada
 * para una calculadora, ya sea mediante teclado, ratón, o ambos.
 * 
 * Además, registra el historial de las operaciones realizadas, mostrando la
 * información correspondiente
 * sobre los números y operadores ingresados, así como los resultados
 * calculados.
 */
public class PantallaAlmacen extends JPanel {

    private JTextArea historialArea;
    private JButton tecladoButton, ratonButton, ambosButton;

    private boolean modoTeclado = false;
    private boolean modoRaton = false;

    private Pantalla pantalla;

    /**
     * Constructor de la clase PantallaAlmacen. Inicializa la interfaz con botones
     * para seleccionar el modo de entrada
     * y un área de texto para mostrar el historial de operaciones.
     * 
     * @param pantalla la pantalla asociada que se actualizará con las entradas y
     *                 resultados.
     */
    public PantallaAlmacen(Pantalla pantalla) {
        setLayout(new BorderLayout());

        this.pantalla = pantalla;

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

        tecladoButton.addActionListener(e -> seleccionarModo("Teclado"));
        ratonButton.addActionListener(e -> seleccionarModo("Ratón"));
        ambosButton.addActionListener(e -> seleccionarModo("Ambos"));

        seleccionarModo("Ambos");

        configurarEventosTeclado();
    }

    /**
     * Lista de escuchas (listeners) que se notificarán cuando se produzca un cambio
     * en el modo de entrada.
     */
    private final List<Runnable> changeListeners = new ArrayList<>();

    /**
     * Añade un escucha que se ejecutará cuando haya un cambio en el modo de
     * entrada.
     * 
     * @param listener el escucha que se ejecutará.
     */
    public void addChangeListener(Runnable listener) {
        changeListeners.add(listener);
    }

    /**
     * Notifica a todos los escuchas registrados que ha habido un cambio.
     */
    private void notificarCambio() {
        for (Runnable listener : changeListeners) {
            listener.run();
        }
    }

    /**
     * Establece el modo de entrada, que puede ser "Teclado", "Ratón" o "Ambos".
     * Actualiza la interfaz y el historial con el modo seleccionado.
     * 
     * @param modo el modo de entrada a seleccionar.
     */
    private void seleccionarModo(String modo) {
        switch (modo) {
            case "Teclado":
                modoTeclado = true;
                modoRaton = false;
                break;
            case "Ratón":
                modoTeclado = false;
                modoRaton = true;
                break;
            case "Ambos":
                modoTeclado = true;
                modoRaton = true;
                break;
        }
        agregarAlHistorial("Modo seleccionado: " + modo);
        notificarCambio();

        if (modoTeclado) {
            this.setFocusable(true);
            this.requestFocusInWindow();
        }
    }

    /**
     * Configura los eventos de teclado para manejar la entrada mediante el teclado,
     * solo si el modo de entrada es "Teclado".
     */
    private void configurarEventosTeclado() {
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (modoTeclado) {
                    manejarEntradaTeclado(e);
                }
            }
        });
    }

    /**
     * Maneja la entrada de teclado, realizando las acciones correspondientes para
     * cada tecla presionada.
     * 
     * @param e el evento de teclado generado.
     */
    public void manejarEntradaTeclado(KeyEvent e) {
        char tecla = e.getKeyChar();

        if (Character.isDigit(tecla)) {
            pantalla.actualizarOperacion(pantalla.getOperacionActual() + tecla);
            agregarAlHistorial("Digitado: " + tecla);
        } else if (tecla == '+') {
            pantalla.actualizarOperacion(pantalla.getOperacionActual() + " + ");
            agregarAlHistorial("Operador: +");
        } else if (tecla == '-') {
            pantalla.actualizarOperacion(pantalla.getOperacionActual() + " - ");
            agregarAlHistorial("Operador: -");
        } else if (tecla == '\n') {
            pantalla.actualizarResultado(pantalla.getOperacionActual());
            agregarAlHistorial("Resultado calculado.");
        } else if (tecla == '\b') {
            String operacion = pantalla.getOperacionActual();
            if (!operacion.isEmpty()) {
                pantalla.actualizarOperacion(operacion.substring(0, operacion.length() - 1));
            }
        } else {
            agregarAlHistorial("Tecla no reconocida: " + tecla);
        }
    }

    /**
     * Maneja la entrada mediante el ratón, registrando la acción de presionar
     * botones.
     * 
     * @param e el evento generado al presionar un botón con el ratón.
     */
    public void manejarEntradaRaton(ActionEvent e) {
        if (modoRaton) {
            JButton boton = (JButton) e.getSource();
            pantalla.actualizarOperacion(pantalla.getOperacionActual() + boton.getText());
        }
    }

    /**
     * Agrega una operación al historial mostrado en la interfaz.
     * 
     * @param operacion la operación que se agregará al historial.
     */
    public void agregarAlHistorial(String operacion) {
        historialArea.append(operacion + "\n");
    }

    /**
     * Obtiene el estado del modo de entrada "Teclado".
     * 
     * @return true si el modo "Teclado" está activado, false en caso contrario.
     */
    public boolean isModoTeclado() {
        return modoTeclado;
    }

    /**
     * Obtiene el estado del modo de entrada "Ratón".
     * 
     * @return true si el modo "Ratón" está activado, false en caso contrario.
     */
    public boolean isModoRaton() {
        return modoRaton;
    }
}
