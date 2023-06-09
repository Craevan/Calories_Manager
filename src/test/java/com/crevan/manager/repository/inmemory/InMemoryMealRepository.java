package com.crevan.manager.repository.inmemory;

import com.crevan.manager.MealTestData;
import com.crevan.manager.UserTestData;
import com.crevan.manager.model.Meal;
import com.crevan.manager.repository.MealRepository;
import com.crevan.manager.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, InMemoryBaseRepository<Meal>> usersMealsMap = new ConcurrentHashMap<>();

    {
        InMemoryBaseRepository<Meal> userMeals = new InMemoryBaseRepository<>();
        MealTestData.meals.forEach(userMeals::put);
        usersMealsMap.put(UserTestData.USER_ID, userMeals);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public Meal save(final Meal meal, final int userId) {
        InMemoryBaseRepository<Meal> meals = usersMealsMap.computeIfAbsent(userId, uId -> new InMemoryBaseRepository<>());
        return meals.save(meal);
    }

    @Override
    public Meal get(final int id, final int userId) {
        InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public boolean delete(final int id, final int userId) {
        InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
        return meals != null && meals.delete(id);
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
        InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
        return meals == null ? Collections.emptyList() :
                meals.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
