package a1;

import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller extends JFrame implements MouseWheelListener{

    private JButton rotateButton;
    private JButton moveButton;

    private Model m;
    private View v;


    private RotateCommand rCommand;
    private MoveCommand mCommand;
    private ColorCommand cCommand;
    private SizeCommand sCommand;



    public Controller()

    {

        m = new Model();
        v = new View(m);

        rCommand = new RotateCommand(m);
        mCommand = new MoveCommand(m);
        cCommand = new ColorCommand(m);
        sCommand = new SizeCommand(m);

        setTitle("Assignment 1");
        setSize(400, 400);


        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);

        rotateButton = new JButton("Rotate");
        rotateButton.setAction(rCommand);
        topPanel.add(rotateButton);


        moveButton = new JButton("Up/Down");
        moveButton.setAction(mCommand);
        topPanel.add(moveButton);

        JComponent contentPane = (JComponent) this.getContentPane();
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap imap = contentPane.getInputMap(mapName);
        KeyStroke cKey = KeyStroke.getKeyStroke('c');
        imap.put(cKey, "color");
        ActionMap amap = contentPane.getActionMap();
        amap.put("color", cCommand);
        this.requestFocus();

        this.addMouseWheelListener(this);

        getContentPane().add(v.getMyCanvas());
        setVisible(true);
        FPSAnimator animator = new FPSAnimator(v.getMyCanvas(), 30);
        animator.start();
    }

    public void mouseWheelMoved(MouseWheelEvent e){
        m.changeSize(e.getWheelRotation());
    }


}
