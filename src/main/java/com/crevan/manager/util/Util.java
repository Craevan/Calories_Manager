package com.crevan.manager.util;

import org.springframework.lang.Nullable;

public class Util {

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(final T value, @Nullable final T start, @Nullable final T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) < 0;
    }
}
