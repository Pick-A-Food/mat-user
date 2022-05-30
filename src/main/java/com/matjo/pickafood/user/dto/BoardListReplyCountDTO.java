package com.matjo.pickafood.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {
    private long boardSeq;

    private String title;

    private String nickname;

    private LocalDateTime regDate;

    private long replyCount;

}
