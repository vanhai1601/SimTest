package com.hitex.yousim.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reply_language")
public class ReplyLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "code_language")
    private String codeLanguage;
    @Column(name = "reply_id")
    private int replyId;
}
