package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "news")
public class News extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "img")
    private String img;
    @Column(name = "types")
    private Integer types;
    @Column(name = "hot")
    private String hot;
    @Column(name = "status")
    private String status;
}
