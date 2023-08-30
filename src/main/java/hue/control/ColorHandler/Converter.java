package hue.control.ColorHandler;

import java.awt.Color;

public class Converter {
    /** Converts hexadecimal colors represented as a String to approximate CIE 1931 x and y coordinates. */
    public static XYPoint hex_to_xy(String hex) {
        Color rgb = ColorHelper.hex_to_rgb(hex);
        return rgb_to_xy(rgb);
    }

    /** Converts red, green and blue integer values to approximate CIE 1931 x and y coordinates. */
    public static XYPoint rgb_to_xy(Color color) {
        XYPoint point = ColorHelper.get_xy_point_from_rgb(color);
        point.x = Math.round(point.x * 1000.0) / 1000.0;
        point.y = Math.round(point.y * 1000.0) / 1000.0;

        return point;
    }

    /** Converts CIE 1931 x and y coordinates and brightness value from 0 to 1 to a CSS hex color. */
    public static String xy_to_hex(XYPoint point, double brightness) {
        Color color = ColorHelper.get_rgb_from_xy_and_brightness(point, brightness);
        return ColorHelper.rgb_to_hex(color);
    }

    /** Converts CIE 1931 x and y coordinates and brightness value from 0 to 1 to a CSS hex color. */
    public static Color xy_to_rgb(XYPoint point, double brightness) {
        Color color = ColorHelper.get_rgb_from_xy_and_brightness(point, brightness);
        return color;
    }
}