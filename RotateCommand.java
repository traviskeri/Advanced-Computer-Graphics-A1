package a1;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class RotateCommand extends AbstractAction{

    Model m;

    public RotateCommand(Model m){
        super("Rotate");
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        m.setRotating(!m.isRotating());
    }

}
