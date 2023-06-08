package com.crevan.manager.web.user;

import com.crevan.manager.model.User;

import java.util.List;

public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(final int id) {
        return super.get(id);
    }

    @Override
    public User getByEmail(final String email) {
        return super.getByEmail(email);
    }

    @Override
    public User create(final User user) {
        return super.create(user);
    }

    @Override
    public void update(final User user, final int id) {
        super.update(user, id);
    }

    @Override
    public void delete(final int id) {
        super.delete(id);
    }
}
