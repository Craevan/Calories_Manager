package com.crevan.manager.repository.jdbc;

import com.crevan.manager.model.Role;
import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertUser;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcUserRepository(final JdbcTemplate jdbcTemplate, final NamedParameterJdbcTemplate template) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = template;
    }

    @Override
    @Transactional
    public User save(final User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number numKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(numKey.intValue());
            insertRoles(user);
        } else {
            if (namedParameterJdbcTemplate.update("""
                    UPDATE users SET name=:name, email=:email, password=:password, registered=:registered,
                             enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id
                            """, parameterSource) == 0) {
                return null;
            }
            // Simplest implementation.
            // More complicated : get user roles from DB and compare them with user.roles (assume that roles are changed rarely).
            // If roles are changed, calculate difference in java and delete/insert them.
            deleteRoles(user);
            insertRoles(user);
        }
        return user;
    }

    @Override
    public User get(final int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return setRoles(DataAccessUtils.singleResult(users));
    }

    @Override
    public User getByEmail(final String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return setRoles(DataAccessUtils.singleResult(users));
    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);

        Map<Integer, Set<Role>> map = new HashMap<>();
        jdbcTemplate.query("SELECT * FROM user_role", rs -> {
            map.computeIfAbsent(rs.getInt("user_id"), userId -> EnumSet.noneOf(Role.class))
                    .add(Role.valueOf(rs.getString("role")));
        });
        users.forEach(u -> u.setRoles(map.get(u.getId())));
        return users;
    }

    @Override
    @Transactional
    public boolean delete(final int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    private void insertRoles(final User u) {
        Set<Role> roles = u.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            jdbcTemplate.batchUpdate("INSERT INTO user_role (user_id, role) VALUES (?, ?)", roles, roles.size(),
                    (ps, role) -> {
                        ps.setInt(1, u.id());
                        ps.setString(2, role.name());
                    });
        }
    }

    private void deleteRoles(final User u) {
        jdbcTemplate.update("DELETE FROM user_role WHERE user_id=?", u.getId());
    }

    private User setRoles(final User u) {
        if (u != null) {
            List<Role> roles = jdbcTemplate.queryForList("SELECT role FROM user_role WHERE user_id=?", Role.class, u.getId());
            u.setRoles(roles);
        }
        return u;
    }
}
