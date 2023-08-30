package hue.control.GUI.Components;

import java.awt.Color;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;

    public Panel (double x, double y, double width, double height, Color color) {
        this.x = (int) x;
        this.y = (int) y;
        this.width = (int) width;
        this.height = (int) height;
        
        setBackground(color);
        setBounds((int) x, (int) y, (int) width, (int) height);
    }

    public void setColor (Color color) {
        setBackground(color);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}