import java.awt.Font;
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
