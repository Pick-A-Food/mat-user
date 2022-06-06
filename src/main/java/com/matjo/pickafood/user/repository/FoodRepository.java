package com.matjo.pickafood.user.repository;

import com.matjo.pickafood.user.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {



}
