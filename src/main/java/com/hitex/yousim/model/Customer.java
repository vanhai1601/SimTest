package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "customer")
public class Customer extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private int custId;
    @Column(name = "cust_name")
    private String cusName;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status_customer")
    private int status;
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "create_source")
    private String createSource;
    @Column(name = "partner_id")
    private int partnerId;
    @Column(name = "sex")
    private int sex;
    @Column(name = "fb")
    private String fb;
    @Column(name = "type_doc")
    private int typeDoc;
    @Column(name = "id_card_back")
    private String idCardBack;
    @Column(name = "id_card_font")
    private String idCardFont;
    @Column(name = "city_id")
    private String cityId;
    @Column(name = "district_id")
    private String districtId;
    @Column(name = "village_id")
    private String villageId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "signature")
    private String signature;
    @Column(name = "id_card")
    private String idCard;

    @Column(name = "date_issue_card")
    private Date dateIssueCard;
    @Column(name = "place_issue_card")
    private String placeIssueCard;
    @Column(name = "session")
    private String session;
    @Column(name = "token")
    private String token;
    @Column(name = "password")
    private String password;
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_id")
    private String provider_id;
}
