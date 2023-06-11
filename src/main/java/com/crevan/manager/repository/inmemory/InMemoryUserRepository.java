package com.crevan.manager.repository.inmemory;

import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    private static final Map<Integer, User> userMap = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(final User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            userMap.put(user.getId(), user);
            return user;
        }
        return userMap.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(final int id) {
        return userMap.get(id);
    }

    @Override
    public User getByEmail(final String email) {
        return userMap.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userMap.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(final int id) {
        return userMap.remove(id) != null;
    }
}
