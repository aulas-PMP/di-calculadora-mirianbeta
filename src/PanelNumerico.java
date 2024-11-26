import java.awt.BasicStroke;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelNumerico extends JPanel {
    private TextField operacion;
    
    public PanelNumerico(){       
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
    }


}