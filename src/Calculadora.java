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
            public void componentMoved(ComponentEvent e) {
                setLocationRelativeTo(null);
            }
        });

        PantallaAlmacen pantallaAlmacen = new PantallaAlmacen();
        add(pantallaAlmacen);
        Pantalla pantalla = new Pantalla();
        add(pantalla);
        PanelNumerico panelNumerico = new PanelNumerico();
        add(panelNumerico);
        PanelOperador panelOperador = new PanelOperador();
        add(panelOperador);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int anchoTotal = getWidth();
                int alturaTotal = getHeight();

                pantallaAlmacen.setBounds(0, 0, getWidth() / 3, getHeight());
                pantalla.setBounds(anchoTotal / 3, 0, (anchoTotal * 2) / 3, alturaTotal / 4);
                panelNumerico.setBounds(anchoTotal / 3, alturaTotal / 4, (anchoTotal * 6) / 15, (alturaTotal * 3) / 4);
                panelOperador.setBounds((anchoTotal * 11) / 15, alturaTotal / 4, (anchoTotal * 4) / 15,
                        (alturaTotal * 3) / 4);

                repaint();

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Calculadora cal = new Calculadora();
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cal.setVisible(true);

    }
}
