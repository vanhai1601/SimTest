package com.hitex.yousim.model.view;

import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Immutable
@Entity
@Data
public class vNewsLanguage {
    @Id
    @Column(name ="id")
    private Integer id;

    @Column(name="types")
    private Integer types;

    @Column(name="img")
    private String img;

    @Column(name="create_time")
    private LocalDateTime createTime;

    @Column(name="update_time")
    private LocalDateTime updateTime;

    @Column(name="create_user")
    private String createUser;

    @Column(name="update_user")
    private String updateUser;

    @Column(name="hot")
    private String hot;

    @Column(name="status")
    private Integer status;

    @Column(name="news_id")
    private Integer newsId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="language")
    private String language;

}
