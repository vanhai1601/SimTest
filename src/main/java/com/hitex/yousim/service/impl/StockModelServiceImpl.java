package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.stockModel.ListStockModelRespone;
import com.hitex.yousim.dto.response.stockModel.StockModelRespone;
import com.hitex.yousim.model.StockModel;
import com.hitex.yousim.repository.StockModelRepository;
import com.hitex.yousim.service.StockModelService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockModelServiceImpl implements StockModelService {
    @Autowired
    private StockModelRepository stockModelRepository;
    @Override
    public ListStockModelRespone getListStockModel(BaseRequestData baseRequestData) throws ApplicationException {
        ListStockModelRespone listStockModelRespone = new ListStockModelRespone();
        try {
            List<StockModel> stockModels = stockModelRepository.findAll();
            List<StockModelRespone> stockModelRespones = new ArrayList<>();
            for(StockModel stockModel:stockModels){
                StockModelRespone stockModelRespone = new StockModelRespone();
                BeanUtils.copyProperties(stockModel,stockModelRespone);
                stockModelRespones.add(stockModelRespone);
            }
            listStockModelRespone.setStockModelResponeList(stockModelRespones);
            return listStockModelRespone;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }
}
