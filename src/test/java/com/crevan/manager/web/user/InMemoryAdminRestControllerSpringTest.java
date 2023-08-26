package com.crevan.manager.web.user;

import com.crevan.manager.repository.inmemory.InMemoryUserRepository;
import com.crevan.manager.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.crevan.manager.UserTestData.NOT_FOUND;
import static com.crevan.manager.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/inmemory.xml"
})
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    public void setup() {
        repository.init();
    }

    @Test
    public void delete() {
        repository.delete(USER_ID);
        Assertions.assertNull(repository.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}
