package com.crevan.manager.repository.datajpa;

import com.crevan.manager.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
}
