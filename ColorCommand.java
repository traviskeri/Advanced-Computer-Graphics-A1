package a1;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class ColorCommand extends AbstractAction{

    Model m;

    public ColorCommand(Model m){
        super("Color");
        this.m =m;
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        m.flipGradient();
        //System.out.println("ColorCommand");
    }
}
