/*import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOperador extends JPanel {

    public PanelOperador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 0.5;
        gbc.weighty = 1;

        // Crear los botones
        JButton buttonClean = new JButton("C");
        JButton buttonAllClean = new JButton("AC");
        JButton buttonMultiply = new JButton("X");
        JButton buttonDivide = new JButton("/");
        JButton buttonAdd = new JButton("+");
        JButton buttonSubstract = new JButton("-");
        JButton buttonResult = new JButton("=");

        Font font = new Font("Arial", Font.PLAIN, 30);  
        buttonClean.setFont(font);
        buttonAllClean.setFont(font);
        buttonMultiply.setFont(font);
        buttonDivide.setFont(font);
        buttonAdd.setFont(font);
        buttonSubstract.setFont(font);
        buttonResult.setFont(font);

        // Añadir los botones al panel con GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(buttonClean, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(buttonAllClean, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonMultiply, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(buttonDivide, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttonAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(buttonSubstract, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  
        add(buttonResult, gbc);
    }
}
*/

/*import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOperador extends JPanel {

    public PanelOperador() {
        setLayout(new GridLayout(4, 1, 5, 5)); // 4 filas, 1 columna, con espacio entre filas

        // Crear las primeras 3 filas como paneles de 2 botones cada una
        JPanel fila1 = crearFila(new JButton("C"), new JButton("AC"));
        JPanel fila2 = crearFila(new JButton("X"), new JButton("/"));
        JPanel fila3 = crearFila(new JButton("+"), new JButton("-"));

        // Botón que ocupará toda la fila inferior
        JButton buttonResult = new JButton("=");
        buttonResult.setFont(new Font("Arial", Font.PLAIN, 30));

        // Agregar las filas al panel principal
        add(fila1);
        add(fila2);
        add(fila3);
        add(buttonResult);
    }

    private JPanel crearFila(JButton button1, JButton button2) {
        // Configurar fuente de los botones
        Font font = new Font("Arial", Font.PLAIN, 30);
        button1.setFont(font);
        button2.setFont(font);

        // Crear panel para una fila con 2 botones
        JPanel fila = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 fila, 2 columnas
        fila.add(button1);
        fila.add(button2);

        return fila;
    }
}*/


import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOperador extends JPanel {

    public PanelOperador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);  // Espaciado entre botones
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Crear los botones
        JButton buttonClean = new JButton("C");
        JButton buttonAllClean = new JButton("AC");
        JButton buttonMultiply = new JButton("X");
        JButton buttonDivide = new JButton("/");
        JButton buttonAdd = new JButton("+");
        JButton buttonSubstract = new JButton("-");
        JButton buttonResult = new JButton("=");

        Font font = new Font("Arial", Font.PLAIN, 30);
        buttonClean.setFont(font);
        buttonAllClean.setFont(font);
        buttonMultiply.setFont(font);
        buttonDivide.setFont(font);
        buttonAdd.setFont(font);
        buttonSubstract.setFont(font);
        buttonResult.setFont(font);

        // Añadir los botones al panel sin configurar la posición aún
        add(buttonClean);
        add(buttonAllClean);
        add(buttonMultiply);
        add(buttonDivide);
        add(buttonAdd);
        add(buttonSubstract);
        add(buttonResult);
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
        gbc.weighty = 1;

        int totalHeight = height - (4 * 4);
        int buttonHeight = totalHeight / 3;

        // Distribuir botones en una cuadrícula de 3x2 para las primeras 6 operaciones
        int number = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weighty = buttonHeight / (double) height;
                JButton boton = (JButton) getComponent(number);
                layout.setConstraints(boton, gbc);
                number++;
            }
        }

        // El botón de resultado ocupa dos columnas
        JButton buttonResult = (JButton) getComponent(6);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        layout.setConstraints(buttonResult, gbc);

        revalidate();
        repaint();
    }
}
