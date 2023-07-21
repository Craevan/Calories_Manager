package com.crevan.manager.repository.datajpa;

import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudMealRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(final CrudMealRepository crudMealRepository, final CrudUserRepository crudUserRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Meal save(final Meal meal, final int userId) {
        if (!meal.isNew() && get(meal.id(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getReferenceById(userId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(final int id, final int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(final int id, final int userId) {
        return crudMealRepository.findById(id)
                .filter(meal -> meal.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return crudMealRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(final LocalDateTime startDateTime, final LocalDateTime endDateTime, final int userId) {
        return crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }
}
