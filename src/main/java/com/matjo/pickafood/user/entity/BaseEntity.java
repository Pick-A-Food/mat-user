package com.matjo.pickafood.user.entity;

import lombok.Getter;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

    @CreatedDate            // 생성할 때만 들어가고 갱신은 못한다는 뜻 updatable = false
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @CreatedDate
    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    private int delFlag;
}
