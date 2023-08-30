package hue.control.GUIDevices;

import java.awt.Color;
import javax.swing.JPanel;

import hue.control.GUI.Components.LightPane;

public class Light {
    protected int brightness;
    protected Color color;
    protected int id;
    protected LightPane lightPane;
    protected JPanel panel;
    protected boolean state;

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        update();
    }

    public int getBrightness() {
        return this.brightness;
    }

    public void setColor(Color color) {
        this.color = color;
        update();
    }

    public Color getColor() {
        return this.color;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void setState(boolean state) {
        this.state = state;
        update();
    }

    public boolean getState() {
        return state;
    }

    protected void update() {}
}