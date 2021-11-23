package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sim_package")
public class SimPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private int packageId;
	@Column(name = "package_code")
	private String packageCode;
	@Column(name = "status")
	private int status;
	@Column(name = "sms_internal")
	private int smsInternal;
	@Column(name = "sms_external")
	private int smsExternal;
	@Column(name = "voice_internal")
	private int voiceInternal;
	@Column(name = "voice_external")
	private int voiceExternal;
	@Column(name = "package_data")
	private int packageData;
	@Column(name = "product_cycle")
	private int productCycle;
	@Column(name = "package_type")
	private int packageType;
	@Column(name = "first_month_price")
	private int firstMonthPrice;
	@Column(name = "mobile_carrier_id")
	private int mobileCarrierId;
	@Column(name = "promotion")
	private Double promotion = 0.0;
}
