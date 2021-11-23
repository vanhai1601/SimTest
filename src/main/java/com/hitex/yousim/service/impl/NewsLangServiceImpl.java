package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.newsLanguage.newsLanguageReq;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.news.NewsRespone;
import com.hitex.yousim.dto.response.newsLanguage.ListNewsLangResponse;
import com.hitex.yousim.dto.response.newsLanguage.newsLanguageResponse;
import com.hitex.yousim.model.News;
import com.hitex.yousim.model.NewsLanguage;
import com.hitex.yousim.model.User;
import com.hitex.yousim.model.view.vNewsLanguage;
import com.hitex.yousim.repository.NewsLangRepository;
import com.hitex.yousim.repository.NewsRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.repository.vnewsLanguageRepository;
import com.hitex.yousim.service.NewsLangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class NewsLangServiceImpl implements NewsLangService {

    @Autowired
    vnewsLanguageRepository vnewsLanguageRepository;
    @Autowired
    NewsLangRepository newsLangRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public ListNewsLangResponse getList(BaseRequestData baseRequestData) throws ApplicationException {
        Locale lang = LocaleContextHolder.getLocale();
        String language = lang.getLanguage();
        ListNewsLangResponse listNewsLangResponse= new ListNewsLangResponse();
        List<newsLanguageResponse> languageResponseList= new ArrayList<>();
        List<vNewsLanguage> list= vnewsLanguageRepository.findAlls(language);
        for(vNewsLanguage vNewsLanguage:list){
            newsLanguageResponse newsLanguageResponse= new newsLanguageResponse();
            BeanUtils.copyProperties(vNewsLanguage,newsLanguageResponse);
            languageResponseList.add(newsLanguageResponse);
        }
        listNewsLangResponse.setLanguageResponseList(languageResponseList);
        return listNewsLangResponse;
    }

    @Override
    public vNewsLanguage create(BaseRequestData requestData) throws ApplicationException {
        newsLanguageReq req= (newsLanguageReq) requestData.getWsRequest();
        BaseResponseData responseData= new BaseResponseData();
        News cretaeNews = new News();
        NewsLanguage creaateNewsLang= new NewsLanguage();
        vNewsLanguage vNewsLanguage= new vNewsLanguage();
        newsLanguageResponse response= new newsLanguageResponse();
        User user= new User();
        try{ if(!user.getToken().equals(requestData.getToken())||!user.getSession().equals(requestData.getSessionId())){
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.QUERY_ERROR));
            responseData.setWsResponse(response);

        }
            cretaeNews.setImg(req.getImg());
            cretaeNews.setStatus("1");
            cretaeNews.setCreateTime(LocalDateTime.now());
            cretaeNews.setHot(req.getHot());
            cretaeNews.setCreateUser(req.getCreateUser());
            cretaeNews.setTypes(req.getTypes());
            newsRepository.save(cretaeNews);
            creaateNewsLang.setNewsId(cretaeNews.getId());
            newsLangRepository.save(creaateNewsLang);
            vNewsLanguage =vnewsLanguageRepository.findById(cretaeNews.getId()).orElse(null);
        }catch (Exception e){
            responseData.setErrorCode("Err");

        }

        return vNewsLanguage;
    }

    @Override
    public ListNewsLangResponse getListDate(BaseRequestData newsRequest) throws ApplicationException {
        Locale lang = LocaleContextHolder.getLocale();
        String language = lang.getLanguage();
        ListNewsLangResponse listNewsLangResponse= new ListNewsLangResponse();
        List<newsLanguageResponse> languageResponseList= new ArrayList<>();
        List<vNewsLanguage> list= vnewsLanguageRepository.findByDate(language);
        for(vNewsLanguage vNewsLanguage:list){
            newsLanguageResponse newsLanguageResponse= new newsLanguageResponse();
            BeanUtils.copyProperties(vNewsLanguage,newsLanguageResponse);
            languageResponseList.add(newsLanguageResponse);
        }
        listNewsLangResponse.setLanguageResponseList(languageResponseList);
        return listNewsLangResponse;
    }

//    @Override
//    public newsLanguageResponse getNewsId(BaseRequestData<newsLanguageReq> requestData) throws ApplicationException {
//        NewsRespone newsRespone= new NewsRespone();
//
//        return null;
//    }

    @Override
    public ListNewsLangResponse getListByPage(BaseRequestData requestData) throws ApplicationException {
        ListNewsLangResponse listNewsLangResponse= new ListNewsLangResponse();
        try{
            newsLanguageReq newsLanguageReq= (newsLanguageReq) requestData.getWsRequest();
            List<vNewsLanguage> newsLanguages= vnewsLanguageRepository.findAllByPage(PageRequest.of(newsLanguageReq.getPage(),newsLanguageReq.getPageSize())) ;
            if(newsLanguages.size()==0){

                throw new  ApplicationException("ERR");
            }
            List<newsLanguageResponse> newsLanguageResponses= new ArrayList<>();
            for(vNewsLanguage vNews :newsLanguages  ){
                newsLanguageResponse newResponse= new newsLanguageResponse();
                BeanUtils.copyProperties(vNews,newResponse);
                newsLanguageResponses.add(newResponse);

            }
            int totalItem = vnewsLanguageRepository.countNews();
            int totalPage = (int) Math.ceil((double) totalItem / (double) newsLanguageReq.getPageSize());
            listNewsLangResponse.setLanguageResponseList(newsLanguageResponses);
            listNewsLangResponse.setTotalPage(totalPage);
            listNewsLangResponse.setTotalItem(totalItem);
        }catch (ApplicationException e){

            throw  e;
        }


        return listNewsLangResponse;
    }

//    @Override
//    public vNewsLanguage updateNews(BaseRequestData requestData) throws ApplicationException {
////        newsLanguageReq req= (newsLanguageReq) requestData.getWsRequest();
////        BaseResponseData responseData= new BaseResponseData();
////        User user= new User();
////        if(!user.getSession().equals(requestData.getSessionId())||!user.getToken().equals(requestData.getToken())){
////
////        }
////        News updateNews = newsRepository.findById(req.getId()).orElse(null);
////        updateNews.setUpdateTime(LocalDateTime.now());
////
////        NewsLanguage updateNewsLang= new NewsLanguage();
////        vNewsLanguage vNewsLanguage= new vNewsLanguage();
////        newsLanguageResponse response= new newsLanguageResponse();
////
////
//        return vNewsLanguage;
//    }



}
