package com.crevan.manager.web;

import static com.crevan.manager.util.MealsUtil.DEFAULT_CALORIES_COUNT;

public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_COUNT;
    }
}
