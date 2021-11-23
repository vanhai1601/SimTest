package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "partner")
public class Partner extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_id")
	private int partnerId;
	@Column(name = "partner_name")
	private String partnerName;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "status_partner")
	private int statusPartner;
	@Column(name = "avatar")
	private String avatar;

	@Column(name = "create_source")
	private String createSource;

}
