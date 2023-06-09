package com.crevan.manager.repository.inmemory;

import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {

    private static final Map<Integer, Meal> database = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger();

    static {
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 500, "Завтрак", LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 1000, "Обед", LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 500, "Ужин", LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 100, "Еда на граничное значение", LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 1000, "Завтрак", LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 500, "Обед", LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0)));
        database.put(counter.incrementAndGet(), new Meal(counter.get(), 410, "Ужин", LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0)));
    }

    @Override
    public Meal save(final Meal meal) {
        meal.setId(counter.incrementAndGet());
        database.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(final int id) {
        return database.get(id);
    }

    @Override
    public Meal update(final Meal meal) {
        return database.computeIfPresent(meal.getId(), (id, currentMeal) -> meal);
    }

    @Override
    public boolean delete(final int id) {
        return database.remove(id) != null;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(database.values());
    }
}
