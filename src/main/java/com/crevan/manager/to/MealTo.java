package com.crevan.manager.to;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealTo {

    private final Integer id;
    private final int calories;
    private final String description;
    private final LocalDateTime dateTime;
    private final boolean excess;

    public MealTo(final Integer id, final int calories, final String description, final LocalDateTime dateTime, final boolean excess) {
        this.id = id;
        this.calories = calories;
        this.description = description;
        this.dateTime = dateTime;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", excess=" + excess +
                '}';
    }
}
