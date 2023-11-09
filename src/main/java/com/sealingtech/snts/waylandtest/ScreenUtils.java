package com.sealingtech.snts.waylandtest;

import javafx.geometry.Rectangle2D;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;

import java.awt.*;

public class ScreenUtils {
    /**
     * Dimensions of the screen
     */
    private static final Rectangle2D SCREEN_DIMENSIONS = Screen.getPrimary().getBounds();

    /**
     * The number of millimeters that fit in one inch
     */
    private static final double MILLIMETERS_PER_INCH = 25.4;

    /**
     * Default size of the UI letterbox
     */
    private static final double DEFAULT_SCENE_SIZE = 820.0;

    /**
     * Size of the UI letterbox when using a wider or narrower screen
     */
    private static final double ALTERNATE_SCENE_SIZE = 600.0;

    /**
     * Default 4:3 ratio used to calculate the scale factor
     */
    public static final double DEFAULT_ASPECT_RATIO = 1.67;

    /**
     * Ratio used to calculate the scale factor when working with screens wider than 4:3
     */
    private static final double WIDE_SCREEN_ASPECT_RATIO = 1.78;

    /**
     * Ratio used to calculate the scale factor when working with screens narrower than 4:3
     */
    private static final double NARROW_SCREEN_ASPECT_RATIO = 1.56;

    /**
     * Get a {@link Scale} for the UI based on the width/height of the screen
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     * @return {@link Scale} Object instantiated with the determined scaleFactor
     */
    public static Scale getScale(double screenWidth, double screenHeight) {
        double scaleFactor = getScaleFactor(screenWidth, screenHeight);
        return new Scale(scaleFactor, scaleFactor);
    }

    /**
     * Get the value the UI should be scaled up/down by based on the width/height of the screen
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     * @return Amount to scale the UI up/down by as a double
     */
    public static double getScaleFactor(double screenWidth, double screenHeight) {
        double aspectRatio = getAspectRatio();

        if (aspectRatio >= WIDE_SCREEN_ASPECT_RATIO) {
            return screenWidth / screenHeight > WIDE_SCREEN_ASPECT_RATIO
                    ? screenHeight / ALTERNATE_SCENE_SIZE
                    : screenWidth / ALTERNATE_SCENE_SIZE;
        } else if (aspectRatio <= NARROW_SCREEN_ASPECT_RATIO) {
            return screenWidth / screenHeight > NARROW_SCREEN_ASPECT_RATIO
                    ? screenHeight / ALTERNATE_SCENE_SIZE
                    : screenWidth / ALTERNATE_SCENE_SIZE;
        }

        return screenWidth / screenHeight > DEFAULT_ASPECT_RATIO
                ? screenHeight / DEFAULT_SCENE_SIZE
                : screenWidth / DEFAULT_SCENE_SIZE;
    }

    /**
     * Get the aspect ratio of the screen being used
     * @return Aspect ratio of the current screen as a double
     */
    public static double getAspectRatio() {
        double aspectRatio = getScreenWidth() / getScreenHeight();
        return Math.round(aspectRatio * 100.0) / 100.0;
    }

    /**
     * Get the ratio used when calculating the scale factor for wider screens
     * @return Wide screen aspect ratio as a double
     */
    public static double getWideScreenAspectRatio() {
        return WIDE_SCREEN_ASPECT_RATIO;
    }

    /**
     * Get the ratio used when calculating the scale factor for narrow screens
     * @return Narrow screen aspect ratio as a double
     */
    public static double getNarrowScreenAspectRatio() {
        return NARROW_SCREEN_ASPECT_RATIO;
    }

    /**
     * Get the width of the screen
     * @return The width of the screen as a double
     */
    public static double getScreenWidth() {
        return SCREEN_DIMENSIONS.getWidth();
    }

    /**
     * Get the height of the screen
     * @return The height of the screen as a double
     */
    public static double getScreenHeight() {
        return SCREEN_DIMENSIONS.getHeight();
    }

    /**
     * Get the number of pixels that corresponds to a size in millimeters
     *
     * @param millimeter The number of millimeters the pixels represent
     * @return The number of pixels that corresponds to the specified size
     */
    public static double getPixelsByMillimeters(double millimeter) {
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        return (millimeter * dpi) / MILLIMETERS_PER_INCH;
    }
}
