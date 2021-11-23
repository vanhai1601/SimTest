package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "place_issue_card")
@Data
public class PlaceIssueCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "city")
    private String city;

    @Column(name = "start_number")
    private String startNumber;
}
