package com.crevan.manager.web;

import com.crevan.manager.model.User;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.crevan.manager.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void getUser() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(final List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, guest, user);
                            }
                        }
                ));
    }
}
