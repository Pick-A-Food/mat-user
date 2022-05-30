package com.matjo.pickafood.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reply",
        indexes =
                {@Index(name = "idx_reply_board_board_seq", columnList = "board_board_seq")})
@Getter
@ToString(exclude = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replySeq;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    
    private Long originReplySeq;
    
    private int replyNum;
    
    private String id;
    
    private String nickname;

    private int likeCount;

    private String mainImage;

    private String profile;

    private String content;

    // 등록 시간
    // 수정 시간
    // 삭제 여부



}
