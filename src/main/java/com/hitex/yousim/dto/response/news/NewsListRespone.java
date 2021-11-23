package com.hitex.yousim.dto.response.news;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.dto.response.address.CityResponse;
import lombok.Data;
import java.util.List;
@Data
public class NewsListRespone  implements IResponseData {
    List<NewsRespone> newsListRespones;

}
