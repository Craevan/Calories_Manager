package com.crevan.manager.web;

import com.crevan.manager.model.AbstractBaseEntity;

import static com.crevan.manager.util.MealsUtil.DEFAULT_CALORIES_COUNT;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

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
