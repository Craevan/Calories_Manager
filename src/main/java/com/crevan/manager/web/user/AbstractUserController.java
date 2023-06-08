package com.crevan.manager.web.user;

import com.crevan.manager.model.User;
import com.crevan.manager.service.UserService;
import org.slf4j.Logger;

import java.util.List;

import static com.crevan.manager.util.ValidationUtil.assureIdConsistent;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {

    protected final Logger log = getLogger(getClass());

    private UserService service;

    public List<User> getAll() {
        log.info("Get All");
        return service.getAll();
    }

    public User get(final int id) {
        log.info("Get User with id={}", id);
        return service.get(id);
    }

    public User getByEmail(final String email) {
        log.info("Get User with email={}", email);
        return service.getByEmail(email);
    }

    public User create(final User user) {
        log.info("Save {}", user);
        return service.create(user);
    }

    public void update(final User user, final int id) {
        log.info("Update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void delete(final int id) {
        log.info("Delete User with id={}", id);
        service.delete(id);
    }
}
