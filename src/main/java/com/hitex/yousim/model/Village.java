package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "village")
public class Village {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "villageid")
	private String villageId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "districtid")
	private String districtId;
}
