package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {
//interface: collection of methods 

    //methods draws graphics
    public void update();
    public void draw (Graphics g);

    //methods of mouse move
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);

    //methods of keyboard 
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);


}
