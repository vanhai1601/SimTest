package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "type_doc")
public class TypeDoc {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private int status;
}
