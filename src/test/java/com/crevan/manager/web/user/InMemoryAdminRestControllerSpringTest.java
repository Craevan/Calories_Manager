package com.crevan.manager.web.user;

import com.crevan.manager.repository.inmemory.InMemoryUserRepository;
import com.crevan.manager.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.crevan.manager.UserTestData.NOT_FOUND;
import static com.crevan.manager.UserTestData.USER_ID;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @Before
    public void setup() {
        repository.init();
    }

    @Test
    public void delete() {
        repository.delete(USER_ID);
        Assert.assertNull(repository.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}
