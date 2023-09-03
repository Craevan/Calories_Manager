package com.crevan.manager.to;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class MealTo {

    private final Integer id;
    private final int calories;
    private final String description;
    private final LocalDateTime dateTime;
    private final boolean excess;

    @ConstructorProperties({"id", "dateTime", "description", "calories", "excess"})
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
    public int hashCode() {
        return Objects.hash(id, dateTime, description, calories, excess);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MealTo mealTo = (MealTo) obj;
        return calories == mealTo.calories &&
                excess == mealTo.excess &&
                Objects.equals(id, mealTo.id) &&
                Objects.equals(dateTime, mealTo.dateTime) &&
                Objects.equals(description, mealTo.description);
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
