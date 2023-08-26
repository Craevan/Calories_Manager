package com.crevan.manager.service.datajpa;

import com.crevan.manager.MealTestData;
import com.crevan.manager.UserTestData;
import com.crevan.manager.model.User;
import com.crevan.manager.service.AbstractUserServiceTest;
import com.crevan.manager.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.MealTestData.MEAL_MATCHER;
import static com.crevan.manager.Profiles.DATAJPA;
import static com.crevan.manager.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    void getWithMeals() {
        User admin = service.getWithMeals(ADMIN_ID);
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
        MEAL_MATCHER.assertMatch(admin.getMeals(), MealTestData.adminMeal2, MealTestData.adminMeal1);
    }

    @Test
    void getWithMealsNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }
}
