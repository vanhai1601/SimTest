package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_function")
public class RoleFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "id_function")
    private int idFunction;
}
