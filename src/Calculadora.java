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
        PantallaAlmacen pantallaAlmacen = new PantallaAlmacen();
        add(pantallaAlmacen, BorderLayout.WEST);
        PanelNumerico panelNumerico = new PanelNumerico();
        add(panelNumerico, BorderLayout.CENTER);
        PanelOperador panelOperador = new PanelOperador();
        add(panelOperador, BorderLayout.EAST);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e){
                int anchoPantallaAlmacen = getWidth()/3;
                int anchoPanelNumerico = getWidth()*2/5;
                int anchoPanelOperador = getWidth()*4/15;
                int alturaPantalla = getHeight()/4;
                pantallaAlmacen.setPreferredSize(new Dimension(anchoPantallaAlmacen, getHeight()));
                panelNumerico.setPreferredSize(new Dimension(anchoPanelNumerico, getHeight()));
                panelOperador.setPreferredSize(new Dimension(anchoPanelOperador, getHeight()));
                pantalla.setPreferredSize(new Dimension(getWidth(), alturaPantalla));
                revalidate();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Calculadora cal = new Calculadora();
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cal.setVisible(true);

    }
}
