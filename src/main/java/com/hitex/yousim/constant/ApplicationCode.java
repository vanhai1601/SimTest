package com.hitex.yousim.constant;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApplicationCode {
    private static final String LANGUAGE_DEFAULT = "vn";
    private static final ApplicationCode instance = new ApplicationCode();
    private static final Map<Integer, String> msg = new HashMap<>();

    private static final String ISO_8859 = "ISO-88n59-1";
    private static final String UTF8 = "UTF-8";
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int NOT_FOUND_USER = 2;
    public static final int DATA_NOT_FOUND = 3;
    public static final int UNAUTHORIZED = 401;
    public static final int UPDATE_PASS_FAILED = 4;
    public static final int EMPTY_LIST_ROLE = 5;
    public static final int ERROR_CREATE_OTP = 6;
    public static final int QUERY_ERROR = 7;
    public static final int ISDN_INVALID = 8;
    public static final int OTP_EXPIRED = 9;
    public static final int USER_VOUCHER_PARTNER_EXIST = 10;
    public static final int NOT_FOUND_CODE = 11;
    public static final int NOT_FOUND_DISCOUNT = 12;
    public static final int ERROR_CALL_ITEL_API = 100;
    public static final int DETAIL_ISDN_NOT_FOUND = 13;
    public static final int DETAIL_KIT_NOT_FOUND = 14;
    public static final int ADDRESS_NOT_FOUND = 15;
    public static final int KIT_NOT_FOUND = 16;
    public static final int ISDN_NOT_FOUND = 17;
    public static final int SIM_EXIST = 18;
    public static final int UPDATE_SIM_FAILED = 19;
    public static final int DELETE_SIM_FAILED = 20;
    public static final int ADD_SIM_FAILED = 22;
    public static final int SIM_NOT_FOUND = 21;
    public static final int NUMBER_HAS_REGISTER = 23;
    public static final int ERROR_SOAP = 24;
    public static final int STOCK_MODEL_NOT_FOUND = 25;
    public static final int API_NOT_FOUND = 26;
    public static final int API_NAME_IS_NOT_NULL = 27;
    public static final int FUNCTION_NAME_IS_NOT_NULL = 28;
    public static final int FUNCTION_NOT_FOUND = 29;
    public static final int API_NAME_ALREADY_EXIST = 30;
    public static final int ID_INVALID = 31;
    public static final int FUNCTION_API_ALREADY_EXIST = 32;
    public static final int ROLE_FUNCTION_ALREADY_EXIST = 33;
    public static final int ROLE_FUNCTION_NOT_FOUND = 34;
    public static final int THE_PHONE_NUMBER_WAS_ORDERED_SOMEWHERE_ELSE = 35;
    public static final int SIM_PACKAGE_NOT_FOUND = 36;
    public static final int ORDER_INVALID = 37;
    public static final int PAYMENT_METHOD_INVALID = 38;
    public static final int PAYMENT_GET_CHECK_SUM_FAIL = 39;
    public static final int TRANS_DONE = 40;
    public static final int MONEY_INVALID = 41;
    public static final int TRANS_FAILED = 42;
    public static final int CHECKSUM_INVALID = 43;
    public static final int ORDER_NOT_FOUND = 44;
    public static final int PARAM_INVALID = 45;
    public static final int REQUEST_INVALID = 46;
    public static final int NOT_FOUND_CUSTOMER = 47;
    public static final int NOT_FOUND_ISDN_BY_STOCK_MODEL = 48;
    public static final int ROLE_NAME_IS_NOT_NULL = 54;
    public static final int ROLE_IS_NOT_EXIST = 55;
    public static final int NOT_REAL_DOC = 49;
    public static final int DOC_INVALID = 50;
    public static final int NOT_FIND_FACE = 51;
    public static final int FACE_NOT_MATCH = 52;
    public static final int FACE_MATCH = 53;
    public static final int DATE_OF_BIRTH_IS_NOT_NULL = 56;
    public static final int DATE_ISSUE_CARD_NOT_NULL = 57;
    public static final int DATE_ISSUE_CARD_NOT_PATTERN = 58;
    public static final int DATE_OF_BIRTH_NOT_PATTERN = 59;
    public static final int EMAIL_EXIST = 60;
    public static final int PHONE_EXIST = 61;
    public static final int CUSTOMER_EXIST = 62;
    public static final int ERROR_CALL_EKYC = 65;
    public static final int CMT_INVALID = 63;
    public static final int CCCD_INVALID = 64;
    public static final int OTP_INVALID = 66;
    public static final int PASSWORD_INVALID = 67;
    public static final int PHONE_OR_PASS_IS_NOT_NULL = 68;
    public static final int OTP_IS_NOT_NULL = 69;
    public static final int EMAIL_MALFORMED = 70;
    public static final int PHONE_AND_EMAIL_IS_NOT_NULL =71;
    public static final int ADDRESS_DELIVERY_IS_NOT_NULL =72;
    public static final int ISDN_EXIST =73;
    public static final int USER_HAS_NO_PERMISSIONS =74;
    public static final int SIM_NOT_EXIST =75;
    public static final int PRODUCT_TYPE_NOT_EXIST =76;
    public static final int HANDLING_ERROR =77;
    public static final int USER_NO_RIGHT =78;
    public static final int YOU_MUST_HAVE_A_SPECIAL_DISCOUNT_CODE_TO_PURCHASE_THIS_NUMBER =79;

    public static final int NOT_FOUND_METHOD_LOGIN=79;
    public static final int ORDER_DOES_NOT_EXIST=80;
    static {
        msg.put(SUCCESS, "Thành Công");
        msg.put(ERROR, "Lỗi");
        msg.put(NOT_FOUND_USER, "Tài khoản không tồn tại");
        msg.put(OTP_INVALID, "Mã OTP bạn vừa nhập không đúng");
        msg.put(UNAUTHORIZED, "Thông tin đăng nhập không hợp lệ");
        msg.put(UPDATE_PASS_FAILED, "Cập nhật mật khẩu không thành công");
        msg.put(EMPTY_LIST_ROLE, "Không lấy được danh sách role");
        msg.put(ERROR_CREATE_OTP, "Không tạo được OTP");
        msg.put(QUERY_ERROR, "Không tìm thấy dữ liệu trong db");
        msg.put(ISDN_INVALID, "Số điện thoại không hợp lệ");
        msg.put(OTP_EXPIRED, "OTP đã hết hạn");
        msg.put(USER_VOUCHER_PARTNER_EXIST, "Tài khoản đối tác đã tồn tại");
        msg.put(NOT_FOUND_CODE, "Không tìm thấy mã voucher");
        msg.put(NOT_FOUND_DISCOUNT, "Không tìm thấy chương trình khuyến mại");
        msg.put(ERROR_CALL_ITEL_API, "Có lỗi khi gọi API đăng ký thông tin : MÃ LỖI KT-ERR-100");
        msg.put(DETAIL_ISDN_NOT_FOUND, "Không tìm thấy số điện thoại trong đơn hàng");
        msg.put(DETAIL_KIT_NOT_FOUND, "Không tìm thấy Kit trong đơn hàng");
        msg.put(ADDRESS_NOT_FOUND, "Không tìm thấy tỉnh thành phố của đơn hàng");
        msg.put(KIT_NOT_FOUND, "KIT không tồn tại");
        msg.put(ISDN_NOT_FOUND, "ISDN không tồn tại");
        msg.put(SIM_EXIST, "Sim đã tồn tại");
        msg.put(UPDATE_SIM_FAILED, "Cập nhật thông tin sim không thành công");
        msg.put(DELETE_SIM_FAILED, "Xoá Sim không thành công");
        msg.put(ADD_SIM_FAILED, "Thêm mới Sim không thành công");
        msg.put(SIM_NOT_FOUND, "Không tìm thấy dữ liệu Sim");
        msg.put(NUMBER_HAS_REGISTER, "Thuê bao đã đăng kí vui lòng nhập số khác");
        msg.put(ERROR_SOAP, "API đăng kí thông tin Itel đang  trả về không phải dạng XML");
        msg.put(STOCK_MODEL_NOT_FOUND, "Không tìm thấy dữ liệu loại mặt hàng");
        msg.put(API_NOT_FOUND, "Không tìm thấy API");
        msg.put(API_NAME_IS_NOT_NULL, "Không được để trống tên API");
        msg.put(FUNCTION_NAME_IS_NOT_NULL, "Không được để trống tên Function");
        msg.put(FUNCTION_NOT_FOUND, "Không tìm thấy Function");
        msg.put(ROLE_FUNCTION_NOT_FOUND, "Không tìm thấy Role Function");
        msg.put(API_NAME_ALREADY_EXIST, "Không được trùng tên API");
        msg.put(ID_INVALID,"ID không hợp lệ");
        msg.put(FUNCTION_API_ALREADY_EXIST,"Function Api Đã Tồn Tại");
        msg.put(ROLE_FUNCTION_ALREADY_EXIST,"Role Function Đã Tồn Tại");
        msg.put(THE_PHONE_NUMBER_WAS_ORDERED_SOMEWHERE_ELSE,"Số điện thoại đã được đặt hàng ở một nơi khác. Vui lòng  chọn số khác để tiếp tục mua hàng.");
        msg.put(SIM_PACKAGE_NOT_FOUND, "Không tìm thấy gói cước quý khách đã chọn.");
        msg.put(ORDER_INVALID, "Đơn hàng không tồn tại.");
        msg.put(PAYMENT_METHOD_INVALID, "Phương thức thanh toán không hợp lệ");
        msg.put(PAYMENT_GET_CHECK_SUM_FAIL, "Check Sum Lỗi");
        msg.put(TRANS_DONE, "Giao dịch đã hoàn thành");
        msg.put(MONEY_INVALID, "Số tiền không hợp lệ");
        msg.put(TRANS_FAILED, "Giao dịch lỗi");
        msg.put(CHECKSUM_INVALID, "Checksum không hợp lệ");
        msg.put(ORDER_NOT_FOUND, "Đơn hàng không tồn tại");
        msg.put(PARAM_INVALID, "Dữ liệu đầu vào không hợp lệ");
        msg.put(REQUEST_INVALID, "Request VNPay không hợp lệ");
        msg.put(NOT_FOUND_CUSTOMER, "Không tìm thấy thông tin khách hàng");
        msg.put(NOT_FOUND_ISDN_BY_STOCK_MODEL, "Không tìm thấy sim phù hợp với loại đã chọn");
        msg.put(NOT_REAL_DOC, "Không phải giấy tờ thật");
        msg.put(DOC_INVALID, "Loại giấy tờ không hợp lệ");
        msg.put(NOT_FIND_FACE, "Không tìm thấy khuôn mặt");
        msg.put(FACE_NOT_MATCH, "Khuôn mặt không khớp");
        msg.put(FACE_MATCH, "Khuôn mặt khớp");
        msg.put(ROLE_NAME_IS_NOT_NULL,"Không được để trống tên quyền");
        msg.put(ROLE_IS_NOT_EXIST,"Quyền không tồn tại");
        msg.put(DATE_OF_BIRTH_IS_NOT_NULL,"Không được để trống ngày tháng năm sinh");
        msg.put(DATE_ISSUE_CARD_NOT_NULL,"Không được để trống ngày cấp CMND/CCCD");
        msg.put(DATE_OF_BIRTH_NOT_PATTERN,"Không đúng định dạng ngày tháng năm sinh. Định dạng phải là dd/mm/yyyy (Ví dụ: 16/12/2000)");
        msg.put(DATE_ISSUE_CARD_NOT_PATTERN,"Không đúng định dạng ngày cấp CMND/CCCD. Định dạng phải là dd/mm/yyyy (Ví dụ: 16/12/2000)");
        msg.put(EMAIL_EXIST,"Email đã tồn tại. Vui lòng nhập email khác.");
        msg.put(PHONE_EXIST,"Số điện thoại đã tồn tại. Vui lòng nhập số điện thoại khác.");
        msg.put(ERROR_CALL_EKYC,"Lỗi khi gọi EKYC");
        msg.put(CMT_INVALID,"Giấy tờ không phải là CMT");
        msg.put(CCCD_INVALID,"Giấy tờ không phải là CCCD");
        msg.put(CUSTOMER_EXIST,"Khách hàng đã tồn tại.");
        msg.put(PASSWORD_INVALID,"Mật khẩu không chính xác");
        msg.put(PHONE_OR_PASS_IS_NOT_NULL,"Số điện thoại và mật khẩu không được để trống");
        msg.put(OTP_IS_NOT_NULL,"Vui lòng nhập mã OTP");
        msg.put(EMAIL_MALFORMED,"Email không đúng định dạng");
        msg.put(PHONE_AND_EMAIL_IS_NOT_NULL,"Số điện thoại và Email không được để trống");
        msg.put(ADDRESS_DELIVERY_IS_NOT_NULL,"Địa chỉ giao hàng không được để trống");
        msg.put(ISDN_EXIST,"ISDN đã tồn tại");
        msg.put(USER_HAS_NO_PERMISSIONS,"Lỗi Api ĐKTT trả về : User không có quyền KIT");
        msg.put(SIM_NOT_EXIST,"Lỗi Api ĐKTT trả về : Sim không tồn tại");
        msg.put(PRODUCT_TYPE_NOT_EXIST,"Loại mặt hàng không tồn tại");
        msg.put(HANDLING_ERROR,"Đã có lỗi trong quá trình xử lí vui lòng thử lại");
        msg.put(USER_NO_RIGHT,"Lỗi Api ĐKTT trả về : User không có quyền kho số");
        msg.put(NOT_FOUND_METHOD_LOGIN, "không hiểu login bằng facebook hay google");
        msg.put(ORDER_DOES_NOT_EXIST, "Đơn hàng của bạn không tồn tại trong hệ thống");
        msg.put(YOU_MUST_HAVE_A_SPECIAL_DISCOUNT_CODE_TO_PURCHASE_THIS_NUMBER,"Bạn phải có voucher VIP để mua số này");
    }

    public static ApplicationCode getInstance() {
        return instance;
    }


    public static String getProperty(int code, String language) {
        return msg.get(code);

    }

    public static String getMessage(int code) {
        return getMsg(code, LANGUAGE_DEFAULT);
    }

//    public static String getMessage(int code, String language) {
//        if (StringUtils.isEmpty(language)) {
//            language = LANGUAGE_DEFAULT;
//        }
//
//        return getMsg(code, language);
//    }

    private static String getMsg(int code, String language) {
        if (msg.containsKey(code)) {
            String message = getProperty(code, language);
            try {
                String msg;
                if (code == 0) {
                    msg = new String(message.getBytes(UTF8), StandardCharsets.UTF_8);
                } else {
                    msg = "[ERR_" + code + "] " + new String(message.getBytes(UTF8), StandardCharsets.UTF_8);
                }
                return msg;
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }

        return "";
    }
}
