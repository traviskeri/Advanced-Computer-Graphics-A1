package a1;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class SizeCommand implements MouseWheelListener {

    Model m;

    public SizeCommand(Model m){
        this.m = m;
    }

    public void mouseWheelMoved(MouseWheelEvent e){
        m.changeSize(e.getWheelRotation());
    }
}
