package hue.control.ColorHandler;

import java.awt.Color;

public class ColorHelper {
    private static XYPoint Red = new XYPoint(0.692, 0.308);
    private static XYPoint Green = new XYPoint(0.17, 0.7);
    private static XYPoint Blue = new XYPoint(0.153, 0.048);

    /** Converts part of a hex string to the red part of an rgb int. */
    public static int hex_to_red(String hex) {
        return Integer.parseInt(hex.substring(0, 2), 16);
    }

    /** Converts part of a hex string to the green part of an rgb int. */
    public static int hex_to_green(String hex) {
        return Integer.parseInt(hex.substring(2, 4), 16);
    }
    
    /** Converts part of a hex string to the blue part of an rgb int. */
    public static int hex_to_blue(String hex) {
        return Integer.parseInt(hex.substring(4, 6), 16);
    }
    
    /** Converts a hex string to an object of the Color class. */
    public static Color hex_to_rgb(String hex) {
        int r = hex_to_red(hex);
        int g = hex_to_green(hex);
        int b = hex_to_blue(hex);

        Color rgb = new Color(r, g, b);
        return rgb;
    }

    /** Converts an instance of the Color class to a hex string. */
    public static String rgb_to_hex(Color color) {
        return Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen()) + Integer.toHexString(color.getBlue());
    }

    /** Returns the cross product of two XYPoints. */
    public static double cross_product(XYPoint p1, XYPoint p2) {
        return (p1.x * p2.y - p1.y * p2.x);
    }

    /** Check if the provided XYPoint can be recreated by a Hue lamp. */
    public static boolean check_point_in_lamps_reach(XYPoint p) {
        XYPoint v1 = new XYPoint(Green.x - Red.x, Green.y - Red.y);
        XYPoint v2 = new XYPoint(Blue.x - Red.x, Blue.y - Red.y);

        XYPoint q = new XYPoint(p.x - Red.x, p.y - Red.y);
        double s = cross_product(q, v2) / cross_product(v1, v2);
        double t = cross_product(v1, q) / cross_product(v1, v2);

        return (s >= 0.0) && (t >= 0.0) && (s + t <= 1.0);
    }

    /** Find the closest point on a line. This point will be reproducible by a Hue lamp. */
    public static XYPoint get_closest_point_to_line(XYPoint A, XYPoint B, XYPoint P) {
        XYPoint AP = new XYPoint(P.x - A.x, P.y - A.y);
        XYPoint AB = new XYPoint(B.x - A.x, B.y - A.y);
        double ab2 = AB.x * AB.x + AB.y * AB.y;
        double ap_ab = AP.x * AB.x + AP.y * AB.y;
        double t = ap_ab / ab2;

        if (t < 0.0) {
            t = 0;
        } else if (t > 1) {
            t = 1;
        }

        return new XYPoint(A.x + AB.x * t, A.y + AB.y * t);
    }

    public static XYPoint get_closest_point_to_point(XYPoint xy_point) {
        // Color is unreproducible, find the closest point on each line in the CIE 1931 'triangle'.
        XYPoint pAB = get_closest_point_to_line(Red, Green, xy_point);
        XYPoint pAC = get_closest_point_to_line(Blue, Red, xy_point);
        XYPoint pBC = get_closest_point_to_line(Green, Blue, xy_point);

        // Gets the distances per point and sees which point is closest to our point.
        double dAB = get_distance_between_two_points(xy_point, pAB);
        double dAC = get_distance_between_two_points(xy_point, pAC);
        double dBC = get_distance_between_two_points(xy_point, pBC);

        double lowest = Math.min(Math.min(dAB, dAC), dBC);
        XYPoint closest_point = null; // Had to do this because the initialization was done in if statements.

        if (lowest == dAB) closest_point = pAB;
        if (lowest == dAC) closest_point = pAC;
        if (lowest == dBC) closest_point = pBC;

        return new XYPoint(closest_point);
    }

    /** Returns the distance between two XYPoints. */
    public static double get_distance_between_two_points(XYPoint one, XYPoint two){
        double dx = one.x - two.x;
        double dy = one.y - two.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /** Returns an XYPoint object containing the closest available CIE 1931 x, y coordinates based on the RGB input values. */
    public static XYPoint get_xy_point_from_rgb(Color color) {
        double red = color.getRed();
        double green = color.getGreen();
        double blue = color.getBlue();

        double r = (red > 0.04045) ? Math.pow(((red + 0.055) / (1 + 0.055)), 2) : (red / 12.92);
        double g = (green > 0.04045) ? Math.pow(((green + 0.055) / (1 + 0.055)), 2) : (green / 12.92);
        double b = (blue > 0.04045) ? Math.pow(((blue + 0.055) / (1 + 0.055)), 2) : (blue / 12.92);

        double X = (r * 0.664511) + (g * 0.154324) + (b * 0.162028);
        double Y = (r * 0.283881) + (g * 0.668433) + (b * 0.047685);
        double Z = (r * 0.000088) + (g * 0.072310) + (b * 0.986039);

        double cx = X / (X + Y + Z);
        double cy = Y / (X + Y + Z);

        // Check if the given XY value is within the colourreach of our lamps.
        XYPoint xy_point = new XYPoint(cx, cy);
        boolean in_reach = check_point_in_lamps_reach(xy_point);

        if (!in_reach) {
            xy_point = get_closest_point_to_point(xy_point);
        }

        return xy_point;
    }

    /** Inverse of `get_xy_point_from_rgb`. Returns (r, g, b) for given x, y values. Implementation of the instructions found on the Philips Hue iOS SDK docs: http://goo.gl/kWKXKl */
    public static Color get_rgb_from_xy_and_brightness(XYPoint point, double brightness) {
        // The xy to color conversion is almost the same, but in reverse order.
        // Check if the xy value is within the color gamut of the lamp.
        // If not continue with step 2, otherwise step 3.
        // We do this to calculate the most accurate color the given light can actually do.
        if (!check_point_in_lamps_reach(point)){
            // Calculate the closest point on the color gamut triangle and use that as xy value See step 6 of color to xy.
            point = get_closest_point_to_point(point);
        }

        // Calculate XYZ values Convert using the following formulas:
        double Y = brightness;
        double X = (Y / point.y) * point.x;
        double Z = (Y / point.y) * (1 - point.x - point.y);

        // Convert to RGB using Wide RGB D65 conversion
        double r = (X * 1.656492) - (Y * 0.354851) - (Z * 0.255038);
        double g = (-X * 0.707196) + (Y * 1.655397) + (Z * 0.036152);
        double b = (X * 0.051713) - (Y * 0.121364) + (Z * 1.011530);

        // Apply reverse gamma correction
        r = (r <= 0.0031308) ? (12.92 * r) : ((1 + 0.055) * Math.pow(r, (1 / 2.4)) - 0.055);
        g = (g <= 0.0031308) ? (12.92 * g) : ((1 + 0.055) * Math.pow(g, (1 / 2.4)) - 0.055);
        b = (b <= 0.0031308) ? (12.92 * b) : ((1 + 0.055) * Math.pow(b, (1 / 2.4)) - 0.055);

        // Bring all negative components to zero
        r = Math.max(0, r);
        g = Math.max(0, g);
        b = Math.max(0, b);

        // If one component is greater than 1, weight components by that value.
        double max = Math.max(r, Math.max(g, b));
        if (max > 1) {
            r /= max;
            g /= max;
            b /= max;
        }

        r *= 255;
        g *= 255;
        b *= 255;

        // Convert the RGB values to your color object The rgb values from the above formulas are between 0.0 and 1.0.
        return new Color((int) r, (int) g, (int) b);
    }
}