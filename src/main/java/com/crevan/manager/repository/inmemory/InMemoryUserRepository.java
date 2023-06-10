package com.crevan.manager.repository.inmemory;

import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    @Override
    public User save(final User user) {
        log.info("Save {}", user);
        return null;
    }

    @Override
    public User get(final int id) {
        log.info("Get User with id={}", id);
        return null;
    }

    @Override
    public User getByEmail(final String email) {
        log.info("Get User with email={}", email);
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("Get All");
        return null;
    }

    @Override
    public boolean delete(final int id) {
        log.info("Delete User with id={}", id);
        return false;
    }
}
