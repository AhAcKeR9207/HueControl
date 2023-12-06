package hue.control;

import java.awt.Color;

public class Constants {
    public static final String ip = "bridgeIP";
    public static final String username = "yourUsernameHere";
    public static final String baseUrl = "http://" + ip + "/api/" + username + "/lights/";
    public static final boolean defaultState = false;
    public static final Color defaultColor = Color.white;
    public static final int defaultBrightness = 100;
}
