package com.crevan.manager.repository.jdbc;

import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepository implements MealRepository {

    @Override
    public Meal save(final Meal meal, final int userId) {
        return null;
    }

    @Override
    public Meal get(final int id, final int userId) {
        return null;
    }

    @Override
    public boolean delete(final int id, final int userId) {
        return false;
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return null;
    }

    @Override
    public List<Meal> getBetweenHalfOpen(final LocalDateTime start, final LocalDateTime end, final int userId) {
        return null;
    }
}
