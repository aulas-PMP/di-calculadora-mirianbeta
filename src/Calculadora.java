import javax.swing.JFrame;
import javax.swing.border.Border;

import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Calculadora extends JFrame {

    public Calculadora() {
        setTitle("Calculadora de Mirian");
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = miPantalla.getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        setSize(anchoPantalla / 2, 600);
        setLocationRelativeTo(null);

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
            public void componentMoved(ComponentEvent e){
                setLocationRelativeTo(null);
            }
        });

        Pantalla pantalla = new Pantalla();
        add(pantalla, BorderLayout.NORTH);

    }

    public static void main(String[] args) throws Exception {
        Calculadora cal = new Calculadora();
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cal.setVisible(true);
        
    }
}
