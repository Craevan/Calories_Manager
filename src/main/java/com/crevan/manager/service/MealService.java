package com.crevan.manager.service;

import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;
import com.crevan.manager.util.DateTimeUtil;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.crevan.manager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(final MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(final int id, final int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(final int id, final int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Meal> getBetweenInclusive(@Nullable final LocalDate start, @Nullable final LocalDate end, final int userId) {
        return repository.getBetweenHalfOpen(
                DateTimeUtil.atStartOfDayOrMin(start),
                DateTimeUtil.atStartOfNextDayOrMax(end),
                userId
        );
    }

    public List<Meal> getAll(final int userId) {
        return repository.getAll(userId);
    }

    public void update(final Meal meal, final int userId) {
        Assert.notNull(meal, "meal must be not null");
        checkNotFoundWithId(repository.save(meal, userId), userId);
    }

    public Meal create(final Meal meal, final int userId) {
        Assert.notNull(meal, "meal must be not null");
        return repository.save(meal, userId);
    }

    public Meal getWithUser(final int id, final int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
