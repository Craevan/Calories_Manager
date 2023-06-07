package com.crevan.manager.util;

import com.crevan.manager.model.Meal;
import com.crevan.manager.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {

    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(500, "Завтрак", LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0)),
                new Meal(1000, "Обед", LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0)),
                new Meal(500, "Ужин", LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0)),
                new Meal(100, "Еда на граничное значение", LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0)),
                new Meal(1000, "Завтрак", LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0)),
                new Meal(500, "Обед", LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0)),
                new Meal(410, "Ужин", LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0))
        );

        List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getCalories(), meal.getDescription(), meal.getDateTime(), excess);
    }
}
