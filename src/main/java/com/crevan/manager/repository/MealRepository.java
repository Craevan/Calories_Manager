package com.crevan.manager.repository;

import com.crevan.manager.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(final Meal meal);

    Meal get(final int id);

    Meal update(final Meal meal);

    boolean delete(final int id);

    List<Meal> getAll();
}
