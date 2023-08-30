package hue.control.GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HueGUI extends JFrame {
    public HueGUI (ArrayList<JPanel> whiteLights, ArrayList<JPanel> colorLights) throws NullPointerException {
        setTitle("Hue GUI");
        setLayout(new GridLayout(2, Math.max(whiteLights.size(), colorLights.size())));

        // Creates two panels that will help organize the lights.
        JPanel whitePanel = new JPanel();
        JPanel colorPanel = new JPanel();

        // Sets up the first panel with all of the white lights in the Main class.  The first is the broadcast light.
        for (JPanel light : whiteLights) {
            int index = whiteLights.indexOf(light);
            light.setBorder(BorderFactory.createTitledBorder("White Light " + (index + 1)));

            whitePanel.add(light);
        }
        whitePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        whitePanel.setLayout(new BoxLayout(whitePanel, BoxLayout.X_AXIS));

        // Sets up the second panel with all of the color lights in the Main class.  The first is the broadcast light.
        for (JPanel light : colorLights) {
            int index = colorLights.indexOf(light);
            light.setBorder(BorderFactory.createTitledBorder("Color Light " + (index + 1)));

            colorPanel.add(light);
        }
        colorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));

        // Adds the two panels to the frame and then loads the GUI.
        add(whitePanel);
        add(colorPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}