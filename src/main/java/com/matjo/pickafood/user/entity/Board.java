package com.matjo.pickafood.user.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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



    public void addImage(String fileLink){
        BoardImage  image = BoardImage.builder().fileLink(fileLink)
                .ord(boardImages.size())
                .build();
        boardImages.add(image);
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @Builder.Default
    private Set<BoardImage> boardImages = new HashSet<>();

    public void addImage(BoardImage boardImage){
        boardImage.fixOrd(boardImages.size());
        boardImages.add(boardImage);
    }

    public void clearImages() {

        boardImages.clear();
    }

}
