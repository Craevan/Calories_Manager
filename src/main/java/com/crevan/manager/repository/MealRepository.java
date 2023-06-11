package com.crevan.manager.repository;

import com.crevan.manager.model.Meal;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository {

    Meal save(final Meal meal, final int userId);

    Meal get(final int id, final int userId);

    boolean delete(final int id, final int userId);

    List<Meal> getAll(final int userId);

    List<Meal> getBetweenHalfOpen(final LocalDateTime start, final LocalDateTime end, final int userId);
}
