package com.matjo.pickafood.user.repository.search;

import com.matjo.pickafood.user.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodSearch {

    Page<Food> searchAll(String[] types, String keyword, Pageable pageable);

}
