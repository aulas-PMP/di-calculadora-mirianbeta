import java.awt.BasicStroke;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelNumerico extends JPanel {
    
    private JButton uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, cero, coma;

    
    public PanelNumerico(){       
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
        setLayout(new GridLayout(4, 3));
        add(siete);
        add(ocho);
        add(nueve);
        add(cuatro);
        add(cinco);
        add(seis);
        add(uno);
        add(dos);
        add(tres);
        add(cero);
        add(coma);
    }


}