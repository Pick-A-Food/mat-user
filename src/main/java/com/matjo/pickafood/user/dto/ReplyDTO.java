package com.matjo.pickafood.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matjo.pickafood.user.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private long replySeq;

    @NotNull
    private long boardSeq;

    private Long originReplySeq;

    private int replyNum;

    @NotEmpty
    private String id;

    @NotEmpty
    private String nickname;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @JsonIgnore
    private LocalDateTime modDate;

    private int delFlag;

    private int likeCount;

    private String mainImage;

    private String profile;

    @NotEmpty
    private String content;


}
