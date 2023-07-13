package com.crevan.manager.util;

import org.springframework.lang.Nullable;

public class Util {

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(final T value, @Nullable final T start, @Nullable final T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }
}
