package com.crevan.manager.web;

import static com.crevan.manager.util.MealsUtil.DEFAULT_CALORIES_COUNT;

public class SecurityUtil {

    private static int id = 1;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(final int id) {
        SecurityUtil.id = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_COUNT;
    }
}
