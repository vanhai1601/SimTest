package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.address.CityRequest;
import com.hitex.yousim.dto.request.address.DistrictRequest;
import com.hitex.yousim.dto.request.address.VillageRequest;
import com.hitex.yousim.dto.response.address.*;
import com.hitex.yousim.model.City;
import com.hitex.yousim.model.District;
import com.hitex.yousim.model.Village;
import com.hitex.yousim.repository.CityRepository;
import com.hitex.yousim.repository.DistrictRepository;
import com.hitex.yousim.repository.VillageRepository;
import com.hitex.yousim.service.AddressService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	VillageRepository villageRepository;

	@Override
	public ListCityResponse listCity(CityRequest cityRequest) throws ApplicationException {
		ListCityResponse listCityResponse = new ListCityResponse();
		List<CityResponse> cityResponses = new ArrayList<>();

		List<City> cityList = cityRepository.findAll();

		for (City city : cityList){
			CityResponse cityResponse = new CityResponse();
			BeanUtils.copyProperties(city, cityResponse);

			cityResponses.add(cityResponse);
		}
		listCityResponse.setCityResponseList(cityResponses);

		return listCityResponse;
	}

	@Override
	public CityResponse city(CityRequest cityRequest) throws ApplicationException{

		CityResponse cityResponse = new CityResponse();
		City city = cityRepository.findById(cityRequest.getCityId()).get();
		BeanUtils.copyProperties(city, cityResponse);

		return cityResponse;
	}

	@Override
	public ListDistrictResponse listDistrict(DistrictRequest districtRequest) throws ApplicationException {

		ListDistrictResponse listDistrictResponse = new ListDistrictResponse();
		List<DistrictResponse> districtResponses = new ArrayList<>();

		List<District> districtList =
				districtRepository.findByCityId(districtRequest.getCityId());//findAll();

		for (District district : districtList){
			DistrictResponse districtResponse = new DistrictResponse();
			BeanUtils.copyProperties(district, districtResponse);

			districtResponses.add(districtResponse);
		}
		listDistrictResponse.setDistrictResponseList(districtResponses);

		return listDistrictResponse;
	}

	@Override
	public DistrictResponse district(DistrictRequest districtRequest) throws ApplicationException {

		DistrictResponse districtResponse = new DistrictResponse();
		District district = districtRepository.findById(districtRequest.getDistrictId()).get();
		BeanUtils.copyProperties(district, districtResponse);

		return districtResponse;
	}

	@Override
	public VillageResponse village(VillageRequest villageRequest) throws ApplicationException {

		VillageResponse villageResponse = new VillageResponse();
		Village village = villageRepository.findById(villageRequest.getVillageId()).get();
		BeanUtils.copyProperties(village, villageResponse);

		return villageResponse;
	}

	@Override
	public ListVillageResponse listVillage(VillageRequest villageRequest) throws ApplicationException {

		ListVillageResponse listVillageResponse = new ListVillageResponse();
		List<VillageResponse> villageResponses = new ArrayList<>();

		List<Village> villageList =
				villageRepository.findByDistrictId(villageRequest.getDistrictId());//findAll();

		for (Village village : villageList){
			VillageResponse villageResponse = new VillageResponse();
			BeanUtils.copyProperties(village, villageResponse);

			villageResponses.add(villageResponse);
		}
		listVillageResponse.setVillageResponseList(villageResponses);

		return listVillageResponse;
	}

}
