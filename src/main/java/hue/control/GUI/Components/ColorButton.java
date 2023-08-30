package hue.control.GUI.Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import hue.control.GUIDevices.Light;

public class ColorButton extends JButton {
    public ColorButton(Light light) {
        setText("Color");
        setAlignmentX(CENTER_ALIGNMENT);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Pick a color", light.getColor());
                light.setColor(color);
            }
        });
    }
}