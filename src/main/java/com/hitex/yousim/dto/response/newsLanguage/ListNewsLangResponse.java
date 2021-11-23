package com.hitex.yousim.dto.response.newsLanguage;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListNewsLangResponse implements IResponseData {
   private int totalPage;
   private int totalItem;
   private List<newsLanguageResponse> languageResponseList;
}
