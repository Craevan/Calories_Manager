package com.crevan.manager.repository.inmemory;

import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;
import com.crevan.manager.util.MealsUtil;
import com.crevan.manager.util.Util;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.crevan.manager.repository.inmemory.InMemoryUserRepository.ADMIN_ID;
import static com.crevan.manager.repository.inmemory.InMemoryUserRepository.USER_ID;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Map<Integer, Map<Integer, Meal>> usersMealsMap = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, USER_ID));
        save(new Meal(510, "Админ ланч", LocalDateTime.of(2015, Month.JUNE, 1, 14, 0)), ADMIN_ID);
        save(new Meal(1500, "Админ ужин", LocalDateTime.of(2015, Month.JUNE, 1, 21, 0)), ADMIN_ID);
    }

    @Override
    public Meal save(final Meal meal, final int userId) {
        Map<Integer, Meal> meals = usersMealsMap.computeIfAbsent(userId, uId -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal get(final int id, final int userId) {
        Map<Integer, Meal> meals = usersMealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public boolean delete(final int id, final int userId) {
        Map<Integer, Meal> meals = usersMealsMap.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(final LocalDateTime start, final LocalDateTime end, final int userId) {
        return filterByPredicate(userId, meal -> Util.isBetweenHalfOpen(meal.getDateTime(), start, end));
    }

    private List<Meal> filterByPredicate(final int userId, final Predicate<Meal> filter) {
        Map<Integer, Meal> meals = usersMealsMap.get(userId);
        return CollectionUtils.isEmpty(meals) ? Collections.emptyList() :
                meals.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
