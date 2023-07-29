package com.crevan.manager.repository;

import com.crevan.manager.model.User;

import java.util.List;

public interface UserRepository {

    User save(final User user);

    User get(final int id);

    User getByEmail(final String email);

    List<User> getAll();

    boolean delete(final int id);

    default User getWithMeals(final int id) {
        throw new UnsupportedOperationException();
    }
}
