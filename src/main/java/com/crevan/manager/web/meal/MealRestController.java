package com.crevan.manager.web.meal;

import com.crevan.manager.model.Meal;
import com.crevan.manager.service.MealService;
import com.crevan.manager.to.MealTo;
import com.crevan.manager.util.MealsUtil;
import com.crevan.manager.util.ValidationUtil;
import com.crevan.manager.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    public MealRestController(final MealService service) {
        this.service = service;
    }

    public Meal get(final int id) {
        int userId = SecurityUtil.authUserId();
        log.info("Get meal with ID={} for user with ID={}", id, userId);
        return service.get(id, userId);
    }

    public void delete(final int id) {
        int userId = SecurityUtil.authUserId();
        log.info("Delete meal with ID={} for user with ID={}", id, userId);
        service.delete(id, userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("Get All for user with ID={}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());

    }

    public Meal create(final Meal meal) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(meal);
        log.info("Create meal={} for user with ID={}", meal, userId);
        return service.create(meal, userId);
    }

    public void update(final Meal meal, final int id) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(meal, id);
        log.info("Update meal={} for user with ID={}", meal, userId);
        service.update(meal, userId);

    }

    public List<MealTo> getBetween(@Nullable final LocalDate startDate, @Nullable final LocalTime startTime,
                                   @Nullable final LocalDate endDate, @Nullable final LocalTime endTime) {

        int userId = SecurityUtil.authUserId();
        log.info("Get Between dates({} - {}) time({} - {}) for user with ID={}", startDate, endDate, startTime, endTime, userId);
        List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        return MealsUtil.getFilteredTos(mealsDateFiltered, MealsUtil.DEFAULT_CALORIES_COUNT, startTime, endTime);
    }
}
