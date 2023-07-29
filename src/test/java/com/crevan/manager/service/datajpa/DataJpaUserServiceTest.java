package com.crevan.manager.service.datajpa;

import com.crevan.manager.MealTestData;
import com.crevan.manager.UserTestData;
import com.crevan.manager.model.User;
import com.crevan.manager.service.AbstractUserServiceTest;
import com.crevan.manager.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.MealTestData.MEAL_MATCHER;
import static com.crevan.manager.Profiles.DATAJPA;
import static com.crevan.manager.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
        MEAL_MATCHER.assertMatch(user.getMeals(), MealTestData.meals);
    }

    @Test
    public void getWithMealsNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }
}
