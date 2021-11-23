package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityid")
	private String cityId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
}
