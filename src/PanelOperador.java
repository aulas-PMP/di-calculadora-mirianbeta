import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * PanelOperador es una clase que representa el panel de operaciones de la
 * calculadora.
 * Este panel contiene botones para las operaciones aritméticas y acciones de la
 * calculadora como limpiar la entrada, reiniciar la calculadora y calcular el
 * resultado.
 */
public class PanelOperador extends JPanel {

    private CalculadoraLogica calculadoraLogica;
    private Pantalla pantalla;
    private PantallaAlmacen pantallaAlmacen;

    /**
     * Constructor que inicializa el panel de operaciones con los botones
     * correspondientes
     * y los agrega a la interfaz gráfica.
     *
     * @param calculadoraLogica la lógica de la calculadora que maneja las
     *                          operaciones aritméticas
     * @param pantalla          la pantalla de la calculadora que muestra las
     *                          operaciones y resultados
     * @param pantallaAlmacen   el panel que almacena el historial de operaciones
     *                          realizadas
     */
    public PanelOperador(CalculadoraLogica calculadoraLogica, Pantalla pantalla, PantallaAlmacen pantallaAlmacen) {
        this.calculadoraLogica = calculadoraLogica;
        this.pantalla = pantalla;
        this.pantallaAlmacen = pantallaAlmacen;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton buttonClean = new JButton("C");
        JButton buttonAllClean = new JButton("AC");
        JButton buttonMultiply = new JButton("X");
        JButton buttonDivide = new JButton("/");
        JButton buttonAdd = new JButton("+");
        JButton buttonSubtract = new JButton("-");
        JButton buttonResult = new JButton("=");

        Font font = new Font("Arial", Font.PLAIN, 30);
        buttonClean.setFont(font);
        buttonAllClean.setFont(font);
        buttonMultiply.setFont(font);
        buttonDivide.setFont(font);
        buttonAdd.setFont(font);
        buttonSubtract.setFont(font);
        buttonResult.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonClean, gbc);
        gbc.gridx = 1;
        add(buttonAllClean, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonMultiply, gbc);
        gbc.gridx = 1;
        add(buttonDivide, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttonAdd, gbc);
        gbc.gridx = 1;
        add(buttonSubtract, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonResult, gbc);

        buttonAdd.addActionListener(e -> manejarOperacion("+"));
        buttonSubtract.addActionListener(e -> manejarOperacion("-"));
        buttonMultiply.addActionListener(e -> manejarOperacion("X"));
        buttonDivide.addActionListener(e -> manejarOperacion("/"));
        buttonResult.addActionListener(e -> calcularResultado());
        buttonClean.addActionListener(e -> limpiarEntrada());
        buttonAllClean.addActionListener(e -> reiniciarCalculadora());
    }

    /**
     * Maneja las operaciones aritméticas. Si la operación es válida, calcula el
     * resultado
     * y actualiza la pantalla con el nuevo valor.
     *
     * @param operacion la operación aritmética seleccionada (+, -, X, /)
     */
    private void manejarOperacion(String operacion) {
        try {
            String operacionTexto = pantalla.getOperacionActual();
            if (!operacionTexto.isEmpty()) {
                double valorActual = Double.parseDouble(operacionTexto);
                double resultado = calculadoraLogica.operar(operacion, valorActual);

                pantalla.actualizarResultado(resultado);
                pantalla.actualizarOperacion("");
                pantallaAlmacen.agregarAlHistorial(operacionTexto + " " + operacion + " = " + resultado);
            }
        } catch (NumberFormatException ex) {
            pantalla.actualizarOperacion("Error");
        }
    }

    /**
     * Calcula el resultado de la operación actual y lo muestra en la pantalla.
     * Si ocurre un error, muestra "Error".
     */
    private void calcularResultado() {
        try {
            double valorActual = obtenerValorPantalla();
            double resultado = calculadoraLogica.operar("", valorActual);
            pantalla.actualizarResultado(resultado);
            pantallaAlmacen.agregarAlHistorial("Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            pantalla.actualizarOperacion("Error");
        }
    }

    /**
     * Limpia la entrada de la pantalla, reiniciando el valor a "0".
     */
    private void limpiarEntrada() {
        pantalla.actualizarOperacion("0");
    }

    /**
     * Reinicia la calculadora, limpiando la pantalla y el historial de operaciones.
     */
    private void reiniciarCalculadora() {
        calculadoraLogica.reiniciar();
        pantalla.actualizarOperacion("0");
        pantalla.actualizarResultado(0);
        pantallaAlmacen.agregarAlHistorial("Calculadora reiniciada");
    }

    /**
     * Obtiene el valor actual de la pantalla para ser utilizado en las operaciones
     * aritméticas.
     * Si la pantalla está vacía, devuelve 0.
     *
     * @return el valor numérico mostrado en la pantalla
     * @throws NumberFormatException si el valor en pantalla no es un número válido
     */
    private double obtenerValorPantalla() throws NumberFormatException {
        String textoPantalla = pantalla.getOperacionActual().replace("Operación: ", "").trim();
        return textoPantalla.isEmpty() ? 0 : Double.parseDouble(textoPantalla);
    }
}
