package com.matjo.pickafood.user.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
public class WordRectangleDTOTests {

    @Test
    public void testSize() {
        WordRectangleDTO wordRectangleDTO = new WordRectangleDTO();
        wordRectangleDTO.setWord("suhong");
        wordRectangleDTO.getPoints().add(new Point(1, 1));
        wordRectangleDTO.getPoints().add(new Point(4, 2));
        wordRectangleDTO.getPoints().add(new Point(3, 5));
        wordRectangleDTO.getPoints().add(new Point(0, 4));
        System.out.println(wordRectangleDTO.getSize());
    }

}
