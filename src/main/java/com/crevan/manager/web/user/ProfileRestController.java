package com.crevan.manager.web.user;

import com.crevan.manager.model.User;

import static com.crevan.manager.web.SecurityUtil.authUserId;

public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(final User user) {
        super.update(user, authUserId());
    }

}
