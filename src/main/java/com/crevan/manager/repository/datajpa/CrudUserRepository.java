package com.crevan.manager.repository.datajpa;

import com.crevan.manager.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") final int id);

    User getByEmail(final String email);

    @EntityGraph(attributePaths = {"meals", "roles"})
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User getWithMeals(final int id);
}
