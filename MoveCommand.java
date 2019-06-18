package a1;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class MoveCommand extends AbstractAction{

    Model m;


    public MoveCommand(Model m){
        super("Move");
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        m.setMoving(!m.isMoving());
       // m.move();
    }


}
