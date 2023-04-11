package main;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.Keyboardinputs;
import inputs.Mouseinputs;

public class GamePanel extends JPanel{
    private Mouseinputs mouseinputs;
    private int xDenta = 100, yDenta = 100;
    public GamePanel(){
        mouseinputs = new Mouseinputs(this);
        addKeyListener(new Keyboardinputs(this));
        addMouseListener(mouseinputs);
        addMouseMotionListener(mouseinputs);

    }

public void changeXdenta(int value){
    this.xDenta += value;
    repaint();
}
public void changeYdenta(int value){
    this.yDenta += value;
    repaint();
}
public void setRectPos(int x, int y){
    this.xDenta = x;
    this.yDenta = y;
    repaint();
}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(xDenta, yDenta, 200, 50);
    }
    
}
