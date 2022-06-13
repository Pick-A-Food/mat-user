package com.matjo.pickafood.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "food")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodSeq; //pk

    @Column(nullable = false, length = 200)
    private String name; //이름

    @Column(nullable = false, length = 200)
    private String companyCategory;

    @Column(length = 50)
    private String company; //회사

    @Column(length = 500)
    private String mainImage; //이미지

    @Column(length = 1000)
    private String refinedAllergyIngredient; //알레르기 성분

    @Column(length = 1000)
    private String ingredient; //재료 성분

    @Column(length = 1000)
    private String sameFactory; //같은 공장

    @Column(length = 500)
    private String image2;

    @Column(length = 500)
    private String image3;

    private int delFlag;
}
