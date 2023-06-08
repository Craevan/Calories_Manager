package com.crevan.manager.model;

import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.crevan.manager.util.MealsUtil.DEFAULT_CALORIES_COUNT;

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private boolean enabled;
    private Date registered = new Date();
    private Set<Role> roles;
    private int caloriesPerDay;

    public User(Integer id, String name, String email, String password, Role... roles) {
        this(id, name, email, password, true, Arrays.asList(roles), DEFAULT_CALORIES_COUNT);
    }

    public User(final Integer id, final String name, final String email, final String password, final boolean enabled, final Collection<Role> roles, final int caloriesPerDay) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.caloriesPerDay = caloriesPerDay;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(final Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(final int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", name='" + name +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }
}
