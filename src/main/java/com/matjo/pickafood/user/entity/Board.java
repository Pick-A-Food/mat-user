package com.matjo.pickafood.user.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "board")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardSeq;
    private int boardCategory;

    private String title;
    private String content;

    private String nickname;
    private String mainImage;


    public void changeTitle(String title){
        this. title = title;
    }
    public void changContent(String content){
        this.content = content;
    }


}
