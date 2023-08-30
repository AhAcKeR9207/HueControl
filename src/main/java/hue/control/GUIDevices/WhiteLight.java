package hue.control.GUIDevices;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import hue.control.Constants;
import hue.control.HttpHandler;
import hue.control.GUI.Components.LightPane;
import hue.control.GUI.Components.Slider;
import hue.control.GUI.Components.ToggleButton;

public class WhiteLight extends Light {
    public WhiteLight (int id) {
        try {
            this.brightness = 100;
            this.id = id;
            this.lightPane = new LightPane(){};
            this.panel = new JPanel();
            this.state = true;

            this.panel.add(this.lightPane);
            this.panel.add(new ToggleButton(this));
            this.panel.add(new Slider(this));
            this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
            update();
        } catch (Exception e) {}
    }

    @Override
    protected void update() {
        // Uses the information attached to the class to create a new color that
        // the physical and digital lights will be set to.
        Color newColor;
        if (state) {
            newColor = new Color(
                (255 * brightness / 100),
                (255 * brightness / 100),
                (255 * brightness / 100)
            );
        } else {
            newColor = Color.black;
        }

        // Creates and loads the JSON that will be given to the light.
        JSONObject newState = new JSONObject();
        newState.put("on", this.state);
        newState.put("bri", this.brightness);

        // Sends the JSON
        HttpHandler.put(Constants.URL + this.id + "/state", newState.toJSONString());

        // Updates the color of the light panel.
        this.lightPane.setBulbColor(newColor);
    }
}