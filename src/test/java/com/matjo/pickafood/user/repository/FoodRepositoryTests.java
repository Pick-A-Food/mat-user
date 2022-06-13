package com.matjo.pickafood.user.repository;

import com.matjo.pickafood.user.domain.Food;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
@Log4j2
public class FoodRepositoryTests {

    @Autowired
    FoodRepository foodRepository;

    @Test
    public void testSearchAll() {
        Pageable pageable = PageRequest.of(0, 10);
        String[] types = {"f", "i"}; // r: refined, n: name, i:ingredient, f:same factory
        String keyword = "분말";

        Page<Food> result = foodRepository.searchAll(types, keyword, pageable);
        List<Food> list = result.getContent();
        list.forEach(food -> {
            log.info(food);
        });
    }

}
