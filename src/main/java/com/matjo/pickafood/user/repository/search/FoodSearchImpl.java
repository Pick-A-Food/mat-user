package com.matjo.pickafood.user.repository.search;

import com.matjo.pickafood.user.domain.Food;
import com.matjo.pickafood.user.domain.QFood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl(){
        super(Food.class);
    }
    @Override
    public Page<Food> searchAll(String[] types, String keyword, Pageable pageable) {

        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);
        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "r":
                        booleanBuilder.or(food.refinedAllergyIngredient.contains(keyword));
                        break;
                    case "n":
                        booleanBuilder.or(food.name.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(food.ingredient.contains(keyword));
                        break;
                    case "f":
                        booleanBuilder.or(food.sameFactory.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(food.foodSeq.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Food> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
