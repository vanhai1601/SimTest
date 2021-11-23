package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.newsLanguage.newsLanguageReq;
import com.hitex.yousim.dto.response.newsLanguage.ListNewsLangResponse;
import com.hitex.yousim.dto.response.newsLanguage.newsLanguageResponse;
import com.hitex.yousim.model.view.vNewsLanguage;
import com.hitex.yousim.utils.exception.ApplicationException;

import java.util.List;

public interface NewsLangService {

    ListNewsLangResponse getList( BaseRequestData baseRequestData) throws ApplicationException;

    vNewsLanguage create(BaseRequestData requestData) throws  ApplicationException;

    ListNewsLangResponse getListDate(BaseRequestData newsRequest) throws ApplicationException;

    ListNewsLangResponse getListByPage(BaseRequestData<newsLanguageReq> requestData) throws ApplicationException;

//    newsLanguageResponse getNewsId(BaseRequestData<newsLanguageReq> requestData) throws ApplicationException;

//    vNewsLanguage updateNews(BaseRequestData requestData) throws ApplicationException;
}
