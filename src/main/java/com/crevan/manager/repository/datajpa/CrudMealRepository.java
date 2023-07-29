package com.crevan.manager.repository.datajpa;

import com.crevan.manager.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") final int id, @Param("userId") final int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("userId") final int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userid " +
            "AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(@Param("startDate") final LocalDateTime startDate,
                                  @Param("endDate") final LocalDateTime endDate,
                                  @Param("userId") final int userId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 AND m.user.id = ?2")
    Meal getWithUser(final int id, final int userid);
}
