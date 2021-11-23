package com.hitex.yousim.controller;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.address.CityRequest;
import com.hitex.yousim.dto.request.address.DistrictRequest;
import com.hitex.yousim.dto.request.address.VillageRequest;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.address.*;
import com.hitex.yousim.service.AddressService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AddressController extends BaseController{
	@Autowired
	AddressService addressService;

	@PostMapping(value = "listCity")
	@ResponseBody
	public ResponseEntity<?> listCity(@RequestBody BaseRequestData<CityRequest> request) throws ApplicationException{
		CityRequest requestData = request.getWsRequest();
		ListCityResponse listCityResponse = addressService.listCity(requestData);
		return success(listCityResponse);
	}

	@PostMapping(value = "listDistrict")
	@ResponseBody
	public ResponseEntity<?> listDistrict(@RequestBody BaseRequestData<DistrictRequest> request) throws ApplicationException {
		DistrictRequest requestData = request.getWsRequest();
		BaseResponseData response = new BaseResponseData();
		ListDistrictResponse listCityResponse = addressService.listDistrict(requestData);
		response.setErrorCode("success");
		response.setMessage(MessageUtils.getMessage("success"));
		response.setWsResponse(listCityResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "listVillage")
	@ResponseBody
	public ResponseEntity<?> listVillage(@RequestBody BaseRequestData<VillageRequest> request) throws ApplicationException{
		VillageRequest requestData = request.getWsRequest();
		BaseResponseData response = new BaseResponseData();
		ListVillageResponse listCityResponse = addressService.listVillage(requestData);
		response.setErrorCode("success");
		response.setMessage(MessageUtils.getMessage("success"));
		response.setWsResponse(listCityResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "detailCity")
	@ResponseBody
	public ResponseEntity<?> city(@RequestBody BaseRequestData<CityRequest> request) throws ApplicationException{
		CityRequest requestData = request.getWsRequest();
		BaseResponseData response = new BaseResponseData();
		CityResponse listCityResponse = addressService.city(requestData);
		response.setErrorCode("success");
		response.setMessage(MessageUtils.getMessage("success"));
		response.setWsResponse(listCityResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "detailDistrict")
	@ResponseBody
	public ResponseEntity<?> district(@RequestBody BaseRequestData<DistrictRequest> request) throws ApplicationException{
		DistrictRequest requestData = request.getWsRequest();
		BaseResponseData response = new BaseResponseData();
		DistrictResponse listCityResponse = addressService.district(requestData);
		response.setErrorCode("success");
		response.setMessage(MessageUtils.getMessage("success"));
		response.setWsResponse(listCityResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "detailVillage")
	@ResponseBody
	public ResponseEntity<?> village(@RequestBody BaseRequestData<VillageRequest> request) throws ApplicationException{
		VillageRequest requestData = request.getWsRequest();
		BaseResponseData response = new BaseResponseData();
		VillageResponse listCityResponse = addressService.village(requestData);
		response.setErrorCode("success");
		response.setMessage(MessageUtils.getMessage("success"));
		response.setWsResponse(listCityResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
