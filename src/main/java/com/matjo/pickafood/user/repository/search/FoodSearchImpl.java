package com.matjo.pickafood.user.repository.search;

import com.matjo.pickafood.user.domain.Food;
import com.matjo.pickafood.user.domain.QFood;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl(){
        super(Food.class);
    }
    @Override
    public Page<Food> searchAll(String[] types, String keyword, Pageable pageable) {

        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);
        return null;
    }
}
