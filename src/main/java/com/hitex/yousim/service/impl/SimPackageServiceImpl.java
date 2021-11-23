package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.simPackage.SimPackageRequest;
import com.hitex.yousim.dto.response.simPackage.ListSimPackageResponse;
import com.hitex.yousim.dto.response.simPackage.SimPackageResponse;
import com.hitex.yousim.model.view.ViewSimPackage;
import com.hitex.yousim.repository.SimPackageRepository;
import com.hitex.yousim.repository.SimPackageSearchAndSortRepository;
import com.hitex.yousim.service.SimPackageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimPackageServiceImpl implements SimPackageService {
    @Autowired
    private SimPackageRepository simPackageRepository;
    @Autowired
    private SimPackageSearchAndSortRepository simPackageSearchAndSortRepository;

    @Override
    public ListSimPackageResponse getSimPackagePromotion(BaseRequestData baseRequestData) throws ApplicationException {
        ListSimPackageResponse listSimPackageResponse = new ListSimPackageResponse();

        try {
            SimPackageRequest simPackageRequest = (SimPackageRequest) baseRequestData.getWsRequest();
            List<ViewSimPackage> viewSimPackageList = simPackageRepository.findSimPackagePromotion(String.valueOf(LocaleContextHolder.getLocale()),PageRequest.of(simPackageRequest.getPage(),simPackageRequest.getPageSize()));
            List<SimPackageResponse> simPackageResponseList = new ArrayList<>();
            for(ViewSimPackage viewSimPackage:viewSimPackageList){
                SimPackageResponse simPackageResponse = new SimPackageResponse();
                BeanUtils.copyProperties(viewSimPackage,simPackageResponse);
                simPackageResponse.setPricePromorion(viewSimPackage.getPrice()*(1 - viewSimPackage.getPromotion()/100));
                simPackageResponseList.add(simPackageResponse);
            }
            int totalItem = simPackageRepository.countSimPackagePromotion(String.valueOf(LocaleContextHolder.getLocale()));
            int totalPage = (int) Math.ceil((double) totalItem / (double) simPackageRequest.getPageSize());
            listSimPackageResponse.setTotalItem(totalItem);
            listSimPackageResponse.setTotalPage(totalPage);
            listSimPackageResponse.setSimPackageResponseList(simPackageResponseList);
            return listSimPackageResponse;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }

    @Override
    public ListSimPackageResponse searchAndSortSimpackage(BaseRequestData baseRequestData) throws ApplicationException {
        try {
            SimPackageRequest simPackageRequest = (SimPackageRequest) baseRequestData.getWsRequest();
            ListSimPackageResponse listSimPackageResponse = new ListSimPackageResponse();
            List<SimPackageResponse> simPackageResponseList = new ArrayList<>();

            String sqlEnd= "";
            if(simPackageRequest.getSortPrice() == 1 && simPackageRequest.getSortDate() == 1){
               sqlEnd = " order by vsp.price, vsp.createTime";
            } else if(simPackageRequest.getSortPrice() == 2 && simPackageRequest.getSortDate() == 1){
                sqlEnd = " order by vsp.price DESC, vsp.createTime ASC";
            } else if(simPackageRequest.getSortPrice() == 1 && simPackageRequest.getSortDate() == -1){
                sqlEnd = " order by vsp.price";
            } else if(simPackageRequest.getSortPrice() == -1 && simPackageRequest.getSortDate() == 1){
                sqlEnd = " order by vsp.createTime";
            }
            List<ViewSimPackage> viewSimPackageList = simPackageSearchAndSortRepository.searchAndSortSimpackage(simPackageRequest.getNationId(),
                    simPackageRequest.getMobileCarrierId(), simPackageRequest.getPackageId(),
                    simPackageRequest.getPrice(),String.valueOf(LocaleContextHolder.getLocale()),
                    simPackageRequest.getDescription(),simPackageRequest.getPage(),simPackageRequest.getPageSize(),sqlEnd);
            if(viewSimPackageList.size() == 0){
                throw new ApplicationException("ERR_0000301");
            }
            for(ViewSimPackage viewSimPackage:viewSimPackageList){
                SimPackageResponse simPackageResponse = new SimPackageResponse();
                BeanUtils.copyProperties(viewSimPackage,simPackageResponse);
                simPackageResponse.setPricePromorion(viewSimPackage.getPrice()*(1 - viewSimPackage.getPromotion()/100));
                simPackageResponseList.add(simPackageResponse);
            }
            long totalItem = simPackageSearchAndSortRepository.totalItem(simPackageRequest.getNationId(),
                    simPackageRequest.getMobileCarrierId(), simPackageRequest.getPackageId(),
                    simPackageRequest.getPrice(),String.valueOf(LocaleContextHolder.getLocale()),
                    simPackageRequest.getDescription());
            int totalPage = (int) Math.ceil((double) totalItem / (double) simPackageRequest.getPageSize());
            listSimPackageResponse.setTotalItem((int) totalItem);
            listSimPackageResponse.setTotalPage(totalPage);
            listSimPackageResponse.setSimPackageResponseList(simPackageResponseList);
            return listSimPackageResponse;
         } catch (ApplicationException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }

    @Override
    public ListSimPackageResponse getListSimPackageByMobileCarrier(BaseRequestData baseRequestData) throws ApplicationException {
        ListSimPackageResponse listSimPackageResponse = new ListSimPackageResponse();
        try {
            SimPackageRequest simPackageRequest = (SimPackageRequest) baseRequestData.getWsRequest();
            List<ViewSimPackage> viewSimPackageList = simPackageRepository.findSimPackageByMobileCarrier(
                    String.valueOf(LocaleContextHolder.getLocale()),simPackageRequest.getMobileCarrierId());
            if(viewSimPackageList.size() == 0){
                throw new ApplicationException("ERR_0000301");
            }
            List<SimPackageResponse> simPackageResponseList = new ArrayList<>();
            for(ViewSimPackage viewSimPackage:viewSimPackageList){
                SimPackageResponse simPackageResponse = new SimPackageResponse();
                BeanUtils.copyProperties(viewSimPackage,simPackageResponse);
                simPackageResponse.setPricePromorion(viewSimPackage.getPrice()*(1 - viewSimPackage.getPromotion()/100));
                simPackageResponseList.add(simPackageResponse);
            }
            listSimPackageResponse.setSimPackageResponseList(simPackageResponseList);
            return listSimPackageResponse;
        } catch (ApplicationException e){
            throw e;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }

    @Override
    public SimPackageResponse getListSimPackageById(BaseRequestData baseRequestData) throws ApplicationException {
        try {
            SimPackageRequest simPackageRequest = (SimPackageRequest) baseRequestData.getWsRequest();
            ViewSimPackage viewSimPackage = simPackageRepository.findSimPackageById(simPackageRequest.getPackageId(), String.valueOf(LocaleContextHolder.getLocale()));
            if(ObjectUtils.isEmpty(viewSimPackage)){
                throw new ApplicationException("ERR_0000301");
            }
            SimPackageResponse simPackageResponse = new SimPackageResponse();
            BeanUtils.copyProperties(viewSimPackage,simPackageResponse);
            simPackageResponse.setPricePromorion(viewSimPackage.getPrice()*(1 - viewSimPackage.getPromotion()/100));
            return simPackageResponse;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }
}
