package com.matjo.pickafood.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private Long foodSeq; //pk
    private String name; //이름
    private String company; //회사

    private String companyCategory;
    private String mainImage; //이미지
    private String refinedAllergyIngredient; //알레르기 성분
    private String ingredient; //재료 성분
    private String sameFactory; //같은 공장

    private String image2;
    private String image3;

    private int num;
}
