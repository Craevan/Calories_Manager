package com.crevan.manager.repository.datajpa;

import com.crevan.manager.model.User;
import com.crevan.manager.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(final CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(final User user) {
        return crudRepository.save(user);
    }

    @Override
    public User get(final int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(final String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public boolean delete(final int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User getWithMeals(final int id) {
        return crudRepository.getWithMeals(id);
    }
}
