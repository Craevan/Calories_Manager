package com.crevan.manager.repository.inmemory;

import com.crevan.manager.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final Map<Integer, T> map = new ConcurrentHashMap<>();

    public T save(final T entity) {
        if (entity.isNew()) {
            entity.setId(counter.incrementAndGet());
            map.put(entity.getId(), entity);
            return entity;
        }

        return map.computeIfPresent(entity.getId(), (id, oldT) -> entity);
    }

    public boolean delete(final int id) {
        return map.remove(id) != null;
    }

    public T get(final int id) {
        return map.get(id);
    }

    Collection<T> getCollection() {
        return map.values();
    }
}
