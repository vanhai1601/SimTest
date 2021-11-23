package com.hitex.yousim.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Immutable
@Entity
@Data
@Table( name = "v_reply")
public class ViewReply {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "img")
    private String img;
    @Column(name = "priority")
    private int priority;
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private int status;
    @Column(name = "code_language")
    private String codeLanguage;
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
