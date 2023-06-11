package com.crevan.manager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {

    private final int calories;
    private final String description;
    private final LocalDateTime dateTime;

    public Meal(final int calories, final String description, final LocalDateTime dateTime) {
        this(null, calories, description, dateTime);
    }

    public Meal(final Integer id, final int calories, final String description, final LocalDateTime dateTime) {
        super(id);
        this.calories = calories;
        this.description = description;
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
