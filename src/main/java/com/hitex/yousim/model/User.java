package com.hitex.yousim.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "user")
@Data
public class User extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "type_user")
    private int typeUser;
    @Column(name = "type_method_login")
    private int typeMethodLogin;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "status_user")
    private int statusUser;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @Column(name = "create_source")
    private String createSource;
    @Column(name = "partner_id")
    private int partnerId;
    @Column(name = "cust_id")
    private int custId;
    @Column(name = "staff_id")
    private int staffId;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "session")
    private String session;
    @Column(name = "token")
    private String token;
    @Column(name = "avatar")
    private String avatar;
}
