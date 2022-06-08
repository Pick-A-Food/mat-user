package com.matjo.pickafood.user.service;

import com.matjo.pickafood.user.domain.Food;
import com.matjo.pickafood.user.dto.FoodDTO;
import com.matjo.pickafood.user.dto.PageRequestDTO;
import com.matjo.pickafood.user.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface FoodService {

    PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO);

    default Food dtoToEntity(FoodDTO foodDTO){

        Food food = Food.builder()
                .foodSeq(foodDTO.getFoodSeq())
                .name(foodDTO.getName())
                .company(foodDTO.getCompany())
                .companyCategory(foodDTO.getCompanyCategory())
                .mainImage(foodDTO.getMainImage())
                .refinedAllergyIngredient(foodDTO.getRefinedAllergyIngredient())
                .ingredient(foodDTO.getIngredient())
                .sameFactory(foodDTO.getSameFactory())
                .build();

        return food;
    }

    default FoodDTO entityToDto(Food food) {

        FoodDTO foodDTO = FoodDTO.builder()
                .foodSeq(food.getFoodSeq())
                .name(food.getName())
                .company(food.getCompany())
                .companyCategory(food.getCompanyCategory())
                .mainImage(food.getMainImage())
                .refinedAllergyIngredient(food.getRefinedAllergyIngredient())
                .ingredient(food.getIngredient())
                .sameFactory(food.getSameFactory())
                .build();

        return foodDTO;
    }
}
