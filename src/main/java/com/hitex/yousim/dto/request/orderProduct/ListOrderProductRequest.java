package com.hitex.yousim.dto.request.orderProduct;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

import java.util.List;

@Data
public class ListOrderProductRequest implements IRequestData {
    int payMethodId;
    String cityId;
    String districtId;
    String villageId;
    String deliveryAddress;
    int custId;
    private String orderCode;
    private String cusName;
    private String address;
    private String email;
    private int typeDoc;
    private  int type;// loại order được mua từ đâu
    private String phone; //Số điện thoại của khách hàng
    private int partnerId;
    private String deliveryCityId;
    private String deliveryDistrictId;
    private String deliveryVillageId;
    private String phoneDelivery;
    private String nameDelivery;

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
    private int price;
//    Sử dụng phương thức vận chuyển xfast. Nhận 1 trong 2 giá trị xteam/none
    private String deliverOption;

    private int sourceChannelSaleId;
    private String userStaff;
    private String codeDelivery;
    private Double totalPrice;
    private String createDate;

    List<OrderRequest> orderRequestList;
    @Override
    public boolean isValid() {
        return false;
    }
}
