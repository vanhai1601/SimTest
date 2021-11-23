package com.hitex.yousim.dto.request.GHTK;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class OrderGHTKRequest implements IRequestData {
    //    Tên thành phố lấy hàng
    private String pickProvince;
    //    Tên quận huyện lấy hàng
    private String pickDistrict;
    //    Tên phường/xã nơi lấy hàng hóa
    private String pickWard;
    //    Tên đường/phố nơi lấy hàng hóa
    private String pickStreet;
    //    Cân nặng của gói hàng, đơn vị sử dụng Gram
    private int weight;
    // lấy ra địa chỉ cụ thể
    private String pickAddress;
    private int price;
    //    Sử dụng phương thức vận chuyển xfast. Nhận 1 trong 2 giá trị xteam/none
    private String deliverOption;
    private String pick_name;
    private String pick_tel;
    private String orderCode;
    private int XFAST;

    private String addressId;
    @Override
    public boolean isValid() {
        return false;
    }
}
