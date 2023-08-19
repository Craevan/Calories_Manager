package com.crevan.manager.web.meal;

import com.crevan.manager.model.Meal;
import com.crevan.manager.service.MealService;
import com.crevan.manager.to.MealTo;
import com.crevan.manager.util.MealsUtil;
import com.crevan.manager.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.crevan.manager.util.ValidationUtil.assureIdConsistent;
import static com.crevan.manager.util.ValidationUtil.checkNew;

public abstract class AbstractMealController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal get(final int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(final int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal create(final Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create meal {} for user {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(final Meal meal, final int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update meal {} for user {}", meal, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<MealTo> getBetween(@Nullable final LocalDate startDate, @Nullable final LocalTime startTime,
                                   @Nullable final LocalDate endDate, @Nullable final LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        return MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

}
