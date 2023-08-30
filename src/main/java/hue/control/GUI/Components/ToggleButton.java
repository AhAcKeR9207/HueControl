package hue.control.GUI.Components;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hue.control.Constants;
import hue.control.GUIDevices.Light;

public class ToggleButton extends JToggleButton {
    public ToggleButton(Light light) {
        // Borders
        Border raisedResized = BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), new EmptyBorder(10, 10, 10, 10));
        Border loweredResized = BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EmptyBorder(10, 10, 10, 10));
        Border active = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 1), loweredResized);
        Border inactive = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 1), raisedResized);

        // Setup
        setAlignmentX(CENTER_ALIGNMENT);
        if (Constants.defaultState) {
            setText("ON");
            setBorder(inactive);
        } else {
            setText("OFF");
            setBorder(active);
        }
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                if (light.getState()) {
                    setText("OFF");
                    setBorder(active);
                    light.setState(false);
                } else {
                    setText("ON");
                    setBorder(inactive);
                    light.setState(true);
                }
            }
        });
    }
}