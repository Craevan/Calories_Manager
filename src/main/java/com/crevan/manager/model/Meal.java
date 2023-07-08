package com.crevan.manager.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {

    private int calories;
    private String description;
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Meal() {
    }

    public Meal(final LocalDateTime dateTime, final String description, final int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(final Integer id, LocalDateTime dateTime, final String description, final int calories) {
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

    public void setCalories(final int calories) {
        this.calories = calories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
