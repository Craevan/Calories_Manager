package com.crevan.manager.model;

import java.time.LocalDateTime;

public class MealTo {

    private final int calories;
    private final String description;
    private final LocalDateTime dateTime;
    private final boolean excess;

    public MealTo(final int calories, final String description, final LocalDateTime dateTime, final boolean excess) {
        this.calories = calories;
        this.description = description;
        this.dateTime = dateTime;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "calories=" + calories +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", excess=" + excess +
                '}';
    }
}
