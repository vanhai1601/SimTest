package com.hitex.yousim.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name ="reply")
@Data
public class Reply extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "img")
    private String img;
    @Column(name = "priority")
    private int priority;
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "status")
    private int status;
}