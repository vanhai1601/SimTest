package com.hitex.yousim.controller;

import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.news.NewsRequest;
import com.hitex.yousim.dto.request.newsLanguage.newsLanguageReq;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.dto.response.news.NewsRespone;
import com.hitex.yousim.dto.response.newsLanguage.ListNewsLangResponse;
import com.hitex.yousim.dto.response.newsLanguage.newsLanguageResponse;
import com.hitex.yousim.model.News;
import com.hitex.yousim.model.NewsLanguage;
import com.hitex.yousim.model.User;
import com.hitex.yousim.model.view.vNewsLanguage;
import com.hitex.yousim.repository.NewsLangRepository;
import com.hitex.yousim.repository.NewsRepository;
import com.hitex.yousim.repository.vnewsLanguageRepository;
import com.hitex.yousim.service.NewsLangService;
import com.hitex.yousim.service.NewsService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/news")
public class NewsController extends BaseController{
    @Autowired
    NewsLangService newsLangService;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsLangRepository newsLangRepository;
    @Autowired
    vnewsLanguageRepository vnewsRepository;
    @ResponseBody
    @PostMapping(value = "/getnewsbyid")
    public ResponseEntity<?> getNewsById(@RequestBody BaseRequestData<newsLanguageReq> requestData) throws ApplicationException {
//        newsLanguageResponse newsLangId=newsLangService.getNewsId(requestData);
//        return new ResponseEntity<>(newsLangId,HttpStatus.OK);
        newsLanguageReq newsRequest= requestData.getWsRequest();
        BaseResponseData response= new BaseResponseData();
        newsLanguageResponse newsRespone= new newsLanguageResponse();
        Locale lang = LocaleContextHolder.getLocale();
        String language = lang.getLanguage();
        vNewsLanguage newsLanguageById= vnewsRepository.findByIdAndLanguage(newsRequest.getNewsId(), language);
        if(newsLanguageById==null){
            response.setMessage(ApplicationCode.getMessage(ApplicationCode.QUERY_ERROR));
            response.setErrorCode(ApplicationCode.getMessage(ApplicationCode.QUERY_ERROR));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.setMessage(MessageUtils.getMessage("success"));
        response.setErrorCode("success");
        BeanUtils.copyProperties(newsLanguageById,newsRespone);
        response.setWsResponse(newsRespone);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping(value = "/getListNewsByDate")
    @ResponseBody
    public  ResponseEntity<?> getNewsByDate(@RequestBody BaseRequestData<newsLanguageReq> newsRequest) throws ApplicationException{
        ListNewsLangResponse listDate= newsLangService.getListDate(newsRequest);
      return new ResponseEntity<>(listDate,HttpStatus.OK);
    }

    @PostMapping(value = "/getnewslist")
    @ResponseBody
    public ResponseEntity<?> getNewsList(@RequestBody BaseRequestData<newsLanguageReq> requestData) throws ApplicationException{
             ListNewsLangResponse list = newsLangService.getList(requestData);
    return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @PostMapping(value = "getListNewsbyPage")
    @ResponseBody
    public ResponseEntity<?> getListByDate(@RequestBody BaseRequestData<newsLanguageReq> requestData)throws ApplicationException{
        try{
            ListNewsLangResponse list = newsLangService.getListByPage(requestData);
        return new  ResponseEntity<>(list,HttpStatus.OK);
    } catch (ApplicationException e) {
        return error(e.getCode(), e.getMessage());
    }
    }

    @PostMapping(value = "/createNews")
    @ResponseBody
    public ResponseEntity<?> createNews(@RequestBody BaseRequestData<newsLanguageReq> requestData) throws ApplicationException{
        vNewsLanguage vNewsLanguage= newsLangService.create(requestData);
        return  new ResponseEntity<>(vNewsLanguage,HttpStatus.OK) ;

    }
    @PostMapping(value = "/updateNews")
    @ResponseBody
    public ResponseEntity<?> updateNews(@RequestBody BaseRequestData<newsLanguageReq> requestData){
//        vNewsLanguage vNewsLanguage= newsLangService.updateNews(requestData);
//        return  new ResponseEntity<>(vNewsLanguage,HttpStatus.OK) ;
        Locale lang = LocaleContextHolder.getLocale();
        String language = lang.getLanguage();
        BaseResponseData responseData= new BaseResponseData();
        newsLanguageResponse newsRespone= new newsLanguageResponse();
        newsLanguageReq request=requestData.getWsRequest();
        News newUpdate= newsRepository.findById(request.getId()).orElse(null);
        NewsLanguage newsLanguage=newsLangRepository.findByIdAndAndLanguage(request.getNewsId(),language);
        vNewsLanguage vnews = vnewsRepository.findByIdByNewidByLang(request.getNewsId(),language);
        User user= newsRepository.findBysessionAndToken(requestData.getToken(),requestData.getSessionId());
        if(newUpdate==null||!user.getSession().equals(requestData.getSessionId())||!user.getToken().equals(requestData.getToken())){
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.UNAUTHORIZED));
            responseData.setErrorCode(ApplicationCode.getMessage(ApplicationCode.QUERY_ERROR));
            return new ResponseEntity<>(responseData,HttpStatus.OK);
        }
        newUpdate.setUpdateUser(user.getUserName());
        newUpdate.setUpdateTime(LocalDateTime.now());
        newUpdate.setHot(request.getHot());
        newUpdate.setImg(request.getImg());
        newsLanguage.setContent(request.getContent());
        newUpdate.setCreateTime(LocalDateTime.now());
        newsRepository.save(newUpdate);
        newsLangRepository.save(newsLanguage);
        responseData.setMessage(MessageUtils.getMessage("success"));
        responseData.setErrorCode("success");
        BeanUtils.copyProperties(vnews,newsRespone);
        responseData.setWsResponse(newsRespone);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
    @PostMapping(value = "/deleteNews")
    @ResponseBody
    public ResponseEntity<?> deleteNews(@RequestBody BaseRequestData<NewsRequest> requestData){

        BaseResponseData responseData= new BaseResponseData();
        NewsRequest request=requestData.getWsRequest();
        News newsDelete = newsRepository.findById(request.getId()).orElse(null);
        User user= newsRepository.findBysessionAndToken(requestData.getToken(),requestData.getSessionId());

        if(newsDelete==null||!user.getSession().equals(requestData.getSessionId())||!user.getToken().equals(requestData.getToken())){

            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.ERROR));
            return new ResponseEntity<>(responseData,HttpStatus.OK);
        }

        newsDelete.setStatus(String.valueOf(Constant.INACTIVE_NEWS));
        newsRepository.save(newsDelete);

        responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.SUCCESS));
        return new ResponseEntity<>(responseData,HttpStatus.OK);

    }
}
