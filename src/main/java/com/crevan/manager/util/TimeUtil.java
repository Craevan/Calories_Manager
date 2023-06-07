package com.crevan.manager.util;

import java.time.LocalTime;

public class TimeUtil {

    public static boolean isBetweenHalfOpen(final LocalTime lt, final LocalTime start, final LocalTime end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) < 0;
    }
}
