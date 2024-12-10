import javax.swing.*;
import java.awt.*;

/**
 * La clase Pantalla es un panel que se utiliza para mostrar la operación actual
 * y el resultado en la interfaz gráfica
 * de una calculadora. La pantalla está dividida en dos etiquetas (JLabel): una
 * para mostrar la operación que se está
 * ingresando y otra para mostrar el resultado de la operación.
 */
public class Pantalla extends JPanel {

    private JLabel operacionLabel;
    private JLabel resultadoLabel;

    /**
     * Constructor de la clase Pantalla. Inicializa el diseño y los componentes
     * visuales,
     * incluyendo las etiquetas para la operación y el resultado.
     */
    public Pantalla() {
        setLayout(new GridLayout(2, 1));

        operacionLabel = new JLabel("0", SwingConstants.RIGHT);
        operacionLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        resultadoLabel = new JLabel("0", SwingConstants.RIGHT);
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        resultadoLabel.setForeground(Color.BLACK);

        add(operacionLabel);
        add(resultadoLabel);
    }

    /**
     * Obtiene la operación actual que se está mostrando en la pantalla.
     * 
     * @return la operación actual como un String.
     */
    public String getOperacionActual() {
        return operacionLabel.getText().trim();
    }

    /**
     * Actualiza la operación mostrada en la pantalla con el valor proporcionado.
     * Si la operación está vacía, la pantalla se restablece a "0".
     * 
     * @param operacion la operación a mostrar en la pantalla.
     */
    public void actualizarOperacion(String operacion) {
        if (operacion.isEmpty()) {
            operacionLabel.setText("0");
        } else {
            operacionLabel.setText(operacion);
        }
    }

    /**
     * Actualiza el resultado mostrado en la pantalla con el valor proporcionado.
     * El resultado se mostrará en color rojo si el valor es negativo.
     * 
     * @param resultado el resultado a mostrar en la pantalla.
     */
    public void actualizarResultado(double resultado) {
        resultadoLabel.setText(String.valueOf(resultado));
        resultadoLabel.setForeground(resultado < 0 ? Color.RED : Color.BLACK);
    }

    /**
     * Actualiza el resultado mostrado en la pantalla a partir de un String.
     * Si el String no puede convertirse a un número válido, se muestra un mensaje
     * de error en la consola.
     * 
     * @param resultado el resultado a mostrar en la pantalla como un String.
     */
    public void actualizarResultado(String resultado) {
        try {
            actualizarResultado(Double.parseDouble(resultado));
        } catch (NumberFormatException ex) {
            System.err.println("El resultado no es un número válido.");
        }
    }

    /**
     * Reinicia la pantalla, restableciendo tanto la operación como el resultado a
     * "0".
     * También se restablece el color del resultado a negro.
     */
    public void reiniciarPantalla() {
        operacionLabel.setText("0");
        resultadoLabel.setText("0");
        resultadoLabel.setForeground(Color.BLACK);
    }
}
