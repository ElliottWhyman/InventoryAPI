package me.itselliott.invetoryapi;

import java.util.ArrayList;
import java.util.List;

public class DisplayRegistry {

    private DisplayRegistry() {

    }

    private static List<Display> displays = new ArrayList<>();

    /**
     * Registes the display in a central list, to allow for easy tracking and managing of open displays
     *
     * @param display display to register
     */
    public static void registerDisplay(Display display) {
        if (!displays.contains(display)) {
            displays.add(display);
        }
    }

    public static void unregisterDisplay(Display display) {
        if (displays.contains(display)) {
            displays.remove(display);
        }
    }

}
