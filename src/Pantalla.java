import javax.swing.*;
import java.awt.*;

public class Pantalla extends JPanel {

    private JLabel operacionLabel;
    private JLabel resultadoLabel;

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

    public String getOperacionActual() {
        return operacionLabel.getText().trim();
    }

    public void actualizarOperacion(String operacion) {
        if (operacion.isEmpty()) {
            operacionLabel.setText("0");
        } else {
            operacionLabel.setText(operacion);
        }
    }

    public void actualizarResultado(double resultado) {
        resultadoLabel.setText(String.valueOf(resultado));
        resultadoLabel.setForeground(resultado < 0 ? Color.RED : Color.BLACK);
    }

    public void reiniciarPantalla() {
        operacionLabel.setText("0");
        resultadoLabel.setText("0");
        resultadoLabel.setForeground(Color.BLACK);
    }
}
