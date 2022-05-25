package com.matjo.pickafood.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer boardSeq;
    private int boardCategory;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String nickname;

    private String mainImage;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private int replyCount;
    private int delFlag;
    private int likeCount;
    private int viewCount;

    @NotEmpty
    private String id;


}
