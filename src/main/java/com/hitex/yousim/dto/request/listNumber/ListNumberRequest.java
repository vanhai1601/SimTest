package com.hitex.yousim.dto.request.listNumber;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

import java.util.List;

@Data
public class ListNumberRequest implements IRequestData {
    private int pageSize;
    private int page;
    private int price;
    Double minPrice;
    Double maxPrice;
    String numberFirst;
    String textSearch;
    List<String> notNumber;
    String textSort;
    @Override
    public boolean isValid() {
        return false;
    }
}
