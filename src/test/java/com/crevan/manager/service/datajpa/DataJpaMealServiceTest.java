package com.crevan.manager.service.datajpa;

import com.crevan.manager.MealTestData;
import com.crevan.manager.model.Meal;
import com.crevan.manager.service.AbstractMealServiceTest;
import com.crevan.manager.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.MealTestData.*;
import static com.crevan.manager.Profiles.DATAJPA;
import static com.crevan.manager.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    void getWithUser() {
        Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertMatch(adminMeal, adminMeal1);
        USER_MATCHER.assertMatch(adminMeal.getUser(), admin);
    }

    @Test
    void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithUser(MealTestData.NOT_FOUND, ADMIN_ID));
    }
}
