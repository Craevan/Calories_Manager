package com.crevan.manager.service;

import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.crevan.manager.util.ValidationUtil.checkNotFound;
import static com.crevan.manager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public User create(final User user) {
        return repository.save(user);
    }

    public void delete(final int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(final int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(final String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(final User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
