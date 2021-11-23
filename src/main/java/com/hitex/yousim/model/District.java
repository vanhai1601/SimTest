package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "district")
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "districtid")
	private String districtId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "cityid")
	private String cityId;
}
