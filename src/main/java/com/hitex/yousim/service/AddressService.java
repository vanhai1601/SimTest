package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.address.CityRequest;
import com.hitex.yousim.dto.request.address.DistrictRequest;
import com.hitex.yousim.dto.request.address.VillageRequest;
import com.hitex.yousim.dto.response.address.*;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.stereotype.Service;

public interface AddressService {

	ListCityResponse listCity(CityRequest cityRequest) throws ApplicationException;

	ListDistrictResponse listDistrict(DistrictRequest districtRequest) throws ApplicationException;

	ListVillageResponse listVillage(VillageRequest villageRequest) throws ApplicationException;

	CityResponse city(CityRequest cityRequest) throws ApplicationException;

	DistrictResponse district(DistrictRequest districtRequest) throws ApplicationException;

	VillageResponse village(VillageRequest villageRequest) throws ApplicationException;

}
