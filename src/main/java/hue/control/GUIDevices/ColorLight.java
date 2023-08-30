package hue.control.GUIDevices;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hue.control.Constants;
import hue.control.HttpHandler;
import hue.control.ColorHandler.Converter;
import hue.control.ColorHandler.XYPoint;
import hue.control.GUI.Components.ColorButton;
import hue.control.GUI.Components.LightPane;
import hue.control.GUI.Components.Slider;
import hue.control.GUI.Components.ToggleButton;

public class ColorLight extends Light {
    /** Creates a new instance of the ColorLight class.
     *  <p>
     *  Due to differences in the Philips Hue software regarding color, this class
     *  only works for a few kinds of Philips Hue lights.  Their model IDs are listed below.
     *  <p>
     *  Models: LCT010, LCT011, LCT012, LCT014, LCT015, LCT016, LLC020, LST002
     *  <p>
     *  For more information, follow this link.  It is a python program that shows how to create
     *  color chooser for Philips Hue lights.
     *  Note that as this is a python program, it is not immediately compatible with this
     *  program, so some conversion will need to take place.
     *  <p>
     *  https://github.com/benknight/hue-python-rgb-converter/blob/master/rgbxy/__init__.py
     */
    public ColorLight(int id) {
        try {
            this.brightness = 100;
            this.color = Color.white;
            this.id = id;
            this.lightPane = new LightPane(){};
            this.panel = new JPanel();
            this.state = true;

            this.panel.add(this.lightPane);
            this.panel.add(new ToggleButton(this));
            this.panel.add(new Slider(this));
            this.panel.add(new ColorButton(this));
            this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
            update();
        } catch (Exception e) {}
    }

    @Override
    protected void update() {
        // Uses the information attached to the class to create a new color that
        // the physical and digital lights will be set to.
        Color newColor;
        XYPoint xyColor;

        System.out.println(this.state);
        if (this.state) {
            newColor = new Color(
                (this.color.getRed() * this.brightness / 100),
                (this.color.getGreen() * this.brightness / 100),
                (this.color.getBlue() * this.brightness / 100)
            );
            xyColor = Converter.rgb_to_xy(newColor);
        } else {
            newColor = Color.black;
            xyColor = Converter.rgb_to_xy(this.color);
        }

        // Updates the color of the light panel.
        this.lightPane.setBulbColor(newColor);

        // Creates a JSON array that is used to store the xy form of color for the lights.
        JSONArray colorArray = new JSONArray();
        colorArray.add(0, xyColor.x);
        colorArray.add(1, xyColor.y);

        // Creates and loads the JSON that will be given to the light.
        JSONObject newState = new JSONObject();
        newState.put("on", this.state);
        newState.put("bri", this.brightness);
        newState.put("xy", colorArray);

        // Sends the JSON
        HttpHandler.put(Constants.URL + this.id + "/state", newState.toJSONString());
    }
}