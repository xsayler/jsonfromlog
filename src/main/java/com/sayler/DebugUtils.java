package com.sayler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sayler
 */
@Slf4j
public class DebugUtils {
    private static final String DEBUG = "debug";
    private static final String ON = "on";

    public static void debugEnable() {
        System.setProperty(DEBUG, ON);
    }

    public static void debug(String message, Object... params) {
        if (ON.equals(System.getProperty(DEBUG))) {
            log.debug(message, params);
        }
    }
}
