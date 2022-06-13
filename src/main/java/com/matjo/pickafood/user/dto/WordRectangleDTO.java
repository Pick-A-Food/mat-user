package com.matjo.pickafood.user.dto;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class WordRectangleDTO {

    private String word;
    private List<Point> points;
    private Double size;

    public WordRectangleDTO() {
        word = "";
        points = new ArrayList<>();
    }

    public double getSize() {
        if (this.size == null) {
            double triangle1 = points.get(0).getX() * (points.get(1).getY() - points.get(2).getY())
                    + points.get(1).getX() * (points.get(2).getY() - points.get(0).getY())
                    + points.get(2).getX() * (points.get(0).getY() - points.get(1).getY());
            double triangle2 = points.get(2).getX() * (points.get(3).getY() - points.get(0).getY())
                    + points.get(3).getX() * (points.get(0).getY() - points.get(2).getY())
                    + points.get(0).getX() * (points.get(2).getY() - points.get(3).getY());
            this.size = (triangle1 + triangle2) / 2.0;
        }
        return this.size / 2.0;
    }

    // x1(y2 − y3) + x2(y3 − y1) + x3(y1− y2) = 0
}


