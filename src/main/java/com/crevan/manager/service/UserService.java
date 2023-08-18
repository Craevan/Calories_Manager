package com.crevan.manager.service;

import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.crevan.manager.util.ValidationUtil.checkNotFound;
import static com.crevan.manager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(final User user) {
        Assert.notNull(user, "user must be not null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(final int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(final int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(final String email) {
        Assert.notNull(email, "email must be not null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(final User user) {
        Assert.notNull(user, "user must be not null");
        checkNotFoundWithId(repository.save(user), user.id());
    }
}
