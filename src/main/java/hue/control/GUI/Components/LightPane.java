package hue.control.GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public abstract class LightPane extends JLayeredPane {
    private JPanel back;
    private JPanel bulb1, bulb2, bulb3, bulb4, bulb5, bulb6, bulb7, bulb8;
    private JPanel decor1, decor2, decor3, decor4, decor5, decor6, decor7, decor8, decor9, decor10, decor11, decor12;

    /** Creates new LightPane */
    public LightPane () {
        // Configures pane
        setAlignmentX(CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(220, 190));
        makePanels();

        JPanel[] decors = {decor1, decor2, decor3, decor4, decor5, decor6, decor7, decor8, decor9, decor10, decor11, decor12};
        for (JPanel decor : decors) {
            add(decor);
        }
        
        JPanel[] bulbs = {bulb1, bulb2, bulb3, bulb4, bulb5, bulb6, bulb7, bulb8};
        for (JPanel bulb : bulbs) {
            add(bulb);
        }

        add(back);
    }

    public void setBulbColor(Color color) {
        bulb1.setBackground(color);
        bulb2.setBackground(color);
        bulb3.setBackground(color);
        bulb4.setBackground(color);
        bulb5.setBackground(color);
        bulb6.setBackground(color);
        bulb7.setBackground(color);
        bulb8.setBackground(color);
    }

    /** 
     * The light needs to be made up of multiple small shapes.
     * Yes, this is necessary.  Believe me, I tried to find an easier way.
     * I would prefer the bulbcolor panel be a single thing, but the bulbs kept rendering weirdly after I updated the colors, so I was forced to split them up.  It did allow me to remove a lot of stuff though!
    */
    public void makePanels() {
        back = new Panel(55, 0, 110, 180, Color.black);
        bulb1 = new Panel(75, 20, 20, 90, Color.white);
        bulb2 = new Panel(65, 30, 10, 60, Color.white);
        bulb3 = new Panel(125, 20, 20, 90, Color.white);
        bulb4 = new Panel(145, 30, 10, 60, Color.white);
        bulb5 = new Panel(85, 100, 50, 30, Color.white);
        bulb6 = new Panel(105, 70, 10, 30, Color.white);
        bulb7 = new Panel(85, 10, 50, 20, Color.white);
        bulb8 = new Panel(105, 30, 10, 30, Color.white);
        decor1 = new Panel(55, 90, 10, 90, Color.white);
        decor2 = new Panel(65, 110, 10, 70, Color.white);
        decor3 = new Panel(75, 130, 10, 50, Color.white);
        decor4 = new Panel(55, 0, 10, 30, Color.white);
        decor5 = new Panel(65, 0, 20, 10, Color.white);
        decor6 = new Panel(155, 90, 10, 90, Color.white);
        decor7 = new Panel(145, 110, 10, 70, Color.white);
        decor8 = new Panel(135, 130, 10, 50, Color.white);
        decor9 = new Panel(155, 0, 10, 30, Color.white);
        decor10 = new Panel(135, 0, 20, 10, Color.white);
        decor11 = new Panel(95, 160, 30, 10, Color.white);
        decor12 = new Panel(95, 140, 30, 10, Color.white);
    }
}