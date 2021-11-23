package com.hitex.yousim.dto.request.customer;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Customer;
import lombok.Data;

import java.sql.Date;


@Data
public class CustomerRequest extends Customer implements IRequestData {
	private int page;
	private int pageSize;
	private String textSearch;
	private String newPass;

	private String dateOfBirthUpdate;
	private String dateIssueCardUpdate;

	private String avatar;
	private String phone;
	private String email;
	private String address;
	private int sex;
	private Date dateOfBirth;
	private String idCard;

	@Override
	public boolean isValid() {
		return false;
	}
}
