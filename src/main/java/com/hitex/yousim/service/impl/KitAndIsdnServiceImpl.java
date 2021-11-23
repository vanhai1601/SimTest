package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.kitAndIsdn.KitAndIsdnRequest;
import com.hitex.yousim.dto.response.kitAndIsdn.KitAndIsdnResponse;
import com.hitex.yousim.dto.response.kitAndIsdn.ListKitAndIsdnResponse;
import com.hitex.yousim.model.*;
import com.hitex.yousim.model.view.KitAndIsdn;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.KitAndIsdnService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class KitAndIsdnServiceImpl implements KitAndIsdnService {
    @Autowired
    private KitAndIsdnRepository kitAndIsdnRepository;
    @Autowired
    private KitRepository kitRepository;
    @Autowired
    private IsdnRepository isdnRepository;
    @Autowired
    private StockModelRepository stockModelRepository;
    @Autowired
    private StockModelPriceRepository stockModelPriceRepository;
    @Autowired
    private StockModelPromotionRepository stockModelPromotionRepository;
    @Autowired
    private KitAndIsdnSearchRepository kitAndIsdnSearchRepository;

    @Override
    public ListKitAndIsdnResponse searchKitAndIsdn(BaseRequestData baseRequestData) throws ApplicationException {
        ListKitAndIsdnResponse listKitAndIsdnResponse = new ListKitAndIsdnResponse();
        try {
            KitAndIsdnRequest kitAndIsdnRequest = (KitAndIsdnRequest) baseRequestData.getWsRequest();
            List<KitAndIsdnResponse> kitAndIsdnResponseList = new ArrayList<>();

            String sqlEnd= "";
            if(kitAndIsdnRequest.getSortPrice() == 1 && kitAndIsdnRequest.getSortDate() == 1){
                sqlEnd = " order by k.priceAmount, k.createTime";
            } else if(kitAndIsdnRequest.getSortPrice() == 2 && kitAndIsdnRequest.getSortDate() == 1){
                sqlEnd = " order by k.priceAmount DESC, k.createTime ASC";
            } else if(kitAndIsdnRequest.getSortPrice() == 1 && kitAndIsdnRequest.getSortDate() == -1){
                sqlEnd = " order by k.priceAmount";
            } else if(kitAndIsdnRequest.getSortPrice() == -1 && kitAndIsdnRequest.getSortDate() == 1){
                sqlEnd = " order by k.createTime";
            }

            List<KitAndIsdn> listKitAndIsdn = kitAndIsdnSearchRepository.searchKitAndIsdn(kitAndIsdnRequest.getNationId(),
                    kitAndIsdnRequest.getMobileCarrierId(), kitAndIsdnRequest.getStockModelId(),kitAndIsdnRequest.getPriceAmount(),
                    kitAndIsdnRequest.getIsdnFirst(),kitAndIsdnRequest.getIsdn(),kitAndIsdnRequest.getPage(),
                    kitAndIsdnRequest.getPageSize(), sqlEnd);
            if(listKitAndIsdn.size() == 0){
                throw new ApplicationException("ERR_0000301");
            }
            for(KitAndIsdn kitAndIsdn : listKitAndIsdn){
                KitAndIsdnResponse kitAndIsdnResponse = new KitAndIsdnResponse();
                BeanUtils.copyProperties(kitAndIsdn,kitAndIsdnResponse);
                kitAndIsdnResponseList.add(kitAndIsdnResponse);
            }
            long totalItem = kitAndIsdnSearchRepository.totalItem(kitAndIsdnRequest.getNationId(),
                    kitAndIsdnRequest.getMobileCarrierId(), kitAndIsdnRequest.getStockModelId(),kitAndIsdnRequest.getPriceAmount(),
                    kitAndIsdnRequest.getIsdnFirst(),kitAndIsdnRequest.getIsdn());
            int totalPage = (int) Math.ceil((double) totalItem / (double) kitAndIsdnRequest.getPageSize());

            listKitAndIsdnResponse.setKitAndIsdnResponseList(kitAndIsdnResponseList);
            listKitAndIsdnResponse.setTotalItem((int) totalItem);
            listKitAndIsdnResponse.setTotalPage(totalPage);

            return listKitAndIsdnResponse;
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }

    @Override
    public ListKitAndIsdnResponse getListKitAndIsdnByStockModelId(BaseRequestData baseRequestData) throws ApplicationException {
        ListKitAndIsdnResponse listKitAndIsdnResponse = new ListKitAndIsdnResponse();
        try {
            KitAndIsdnRequest kitAndIsdnRequest = (KitAndIsdnRequest) baseRequestData.getWsRequest();
            List<KitAndIsdn> listKitAndIsdn = kitAndIsdnRepository.findByStockModelId(kitAndIsdnRequest.getStockModelId(),PageRequest.of(kitAndIsdnRequest.getPage(),kitAndIsdnRequest.getPageSize()));

            if(listKitAndIsdn.size() == 0){
                throw new ApplicationException("ERR_0000301");
            }
            List<KitAndIsdnResponse> kitAndIsdnResponseList = new ArrayList<>();
            for(KitAndIsdn kitAndIsdn:listKitAndIsdn){
                KitAndIsdnResponse kitAndIsdnResponse = new KitAndIsdnResponse();
                BeanUtils.copyProperties(kitAndIsdn,kitAndIsdnResponse);
                kitAndIsdnResponseList.add(kitAndIsdnResponse);
            }
            int totalItem = kitAndIsdnRepository.countKitAndIsdnByStockModelId(kitAndIsdnRequest.getStockModelId());
            int totalPage = (int) Math.ceil((double) totalItem / (double) kitAndIsdnRequest.getPageSize());
            listKitAndIsdnResponse.setKitAndIsdnResponseList(kitAndIsdnResponseList);
            listKitAndIsdnResponse.setTotalItem(totalItem);
            listKitAndIsdnResponse.setTotalPage(totalPage);
        } catch (ApplicationException e){
            throw e;
        }
        return listKitAndIsdnResponse;
    }


    @Override
    public ListKitAndIsdnResponse setPromotionRandom(BaseRequestData baseRequestData) throws ApplicationException {
        ListKitAndIsdnResponse listKitAndIsdnResponse = new ListKitAndIsdnResponse();
        List<KitAndIsdn> kitAndIsdnRanDomList = new ArrayList<>();
        try{
            KitAndIsdnRequest kitAndIsdnRequest = (KitAndIsdnRequest) baseRequestData.getWsRequest();
            if(kitAndIsdnRequest.getStockModelId() == 0){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("stockModelId"));
            }
            if(kitAndIsdnRequest.getRandomSize() == 0){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("randomSize"));
            }
            if(kitAndIsdnRequest.getToStockModelId() == 0){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("toStockModelId"));
            }
            List<KitAndIsdn> kitAndIsdnList = kitAndIsdnRepository.findKitAndIsdnByType(kitAndIsdnRequest.getStockModelId());
            if(ObjectUtils.isEmpty(kitAndIsdnList)){
                throw new ApplicationException("ERR_0000302");
            }
            Random random = new Random();
            for(int i = 0; i < Math.min(kitAndIsdnRequest.getRandomSize(),kitAndIsdnList.size());i++){
                kitAndIsdnRanDomList.add(kitAndIsdnList.remove(random.nextInt(kitAndIsdnList.size())));
            }

            StockModel stockModelType = stockModelRepository.findStockModelById(kitAndIsdnRequest.getStockModelId());
            StockModel stockModelPromotion = stockModelRepository.findStockModelById(kitAndIsdnRequest.getToStockModelId());
            if(ObjectUtils.isEmpty(stockModelPromotion)){
                throw new ApplicationException("ERR_0000303");
            }
            StockModelPrice stockModelPricePromotion = stockModelPriceRepository.findSmpByStockModelId(stockModelPromotion.getStockModelId());
            StockModelPrice stockModelPriceType = stockModelPriceRepository.findSmpByStockModelId(stockModelType.getStockModelId());
            if(stockModelPromotion.getStockModelId() == 4 ){
                stockModelPricePromotion.setPriceAmount(stockModelPriceType.getPriceAmount());
            }
            stockModelPriceRepository.save(stockModelPricePromotion);

            List<KitAndIsdnResponse> kitAndIsdnResponseList = new ArrayList<>();

            for(KitAndIsdn kitAndIsdn:kitAndIsdnRanDomList){
                if(kitAndIsdn.getSerial().equals("0")){
                    Isdn isdn = isdnRepository.findIsdnByIsdn(kitAndIsdn.getIsdn());
                    isdn.setStockModelId(stockModelPromotion.getStockModelId());
                    isdnRepository.save(isdn);
                } else {
                    KIT kit = kitRepository.findKitByIsdn(kitAndIsdn.getIsdn());
                    kit.setStockModelId(stockModelPromotion.getStockModelId());
                    kitRepository.save(kit);

                }
                KitAndIsdnResponse kitAndIsdnResponse = new KitAndIsdnResponse();
                BeanUtils.copyProperties(kitAndIsdn,kitAndIsdnResponse);
                kitAndIsdnResponseList.add(kitAndIsdnResponse);
            }

            listKitAndIsdnResponse.setKitAndIsdnResponseList(kitAndIsdnResponseList);
            return listKitAndIsdnResponse;
        } catch (ApplicationException e){
            throw e;
        }
    }

    @Override
    public ListKitAndIsdnResponse getKitAndIsdnByPromotion(BaseRequestData baseRequestData) throws ApplicationException {
        ListKitAndIsdnResponse listKitAndIsdnResponse = new ListKitAndIsdnResponse();
        KitAndIsdnRequest kitAndIsdnRequest = (KitAndIsdnRequest) baseRequestData.getWsRequest();
        try{
            List<KitAndIsdn> kitAndIsdns = kitAndIsdnRepository.findKitAndIsdnByPromotion(kitAndIsdnRequest.getStockModelId(),
                    PageRequest.of(kitAndIsdnRequest.getPage(),kitAndIsdnRequest.getPageSize()));
            List<KitAndIsdnResponse> kitAndIsdnResponseList = new ArrayList<>();

            if(kitAndIsdns.size() == 0 ){
                throw new ApplicationException("ERR_0000301");
            }

            for(KitAndIsdn kitAndIsdn:kitAndIsdns){
                KitAndIsdnResponse kitAndIsdnResponse = new KitAndIsdnResponse();
                BeanUtils.copyProperties(kitAndIsdn,kitAndIsdnResponse);
                if(kitAndIsdn.getPromotion() == null){
                    throw new ApplicationException("ERR_0000301");
                }
                if(stockModelPromotionRepository.findbyStockModelPromotionId(kitAndIsdn.getStockModelId()).getType() == 1){
                    kitAndIsdnResponse.setPricePromotion(kitAndIsdn.getPriceAmount()*(1.0 - kitAndIsdn.getPromotion()/100));
                } else if(stockModelPromotionRepository.findbyStockModelPromotionId(kitAndIsdn.getStockModelId()).getType() == 2){
                    kitAndIsdnResponse.setPricePromotion(kitAndIsdn.getPriceAmount() - kitAndIsdn.getPromotion());
                }
                kitAndIsdnResponseList.add(kitAndIsdnResponse);
            }
            int totalItem = kitAndIsdnRepository.conutKitAndIsdnByPromotion(kitAndIsdnRequest.getStockModelId());
            int totalPage = (int) Math.ceil((double) totalItem / (double) kitAndIsdnRequest.getPageSize());
            listKitAndIsdnResponse.setKitAndIsdnResponseList(kitAndIsdnResponseList);
            listKitAndIsdnResponse.setTotalItem(totalItem);
            listKitAndIsdnResponse.setTotalPage(totalPage);
        } catch(ApplicationException e) {
            throw e;
        }
        return listKitAndIsdnResponse;
    }

    @Override
    public KitAndIsdnResponse getKitAndIsdnDetail(BaseRequestData baseRequestData) throws ApplicationException {
        KitAndIsdnResponse kitAndIsdnResponse = new KitAndIsdnResponse();
        try {
            KitAndIsdnRequest kitAndIsdnRequest = (KitAndIsdnRequest) baseRequestData.getWsRequest();
            KitAndIsdn kitAndIsdn = kitAndIsdnRepository.findKitAndIsdnByIsdn(kitAndIsdnRequest.getIsdn());
            BeanUtils.copyProperties(kitAndIsdn,kitAndIsdnResponse);
            return kitAndIsdnResponse;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }
}
