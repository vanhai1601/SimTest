package com.hitex.yousim.utils;

import com.hitex.yousim.dto.request.payment.IpnUrlRequest;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Log4j2
public class PaymentUtils {
    public static String Sha256VnPay(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (UnsupportedEncodingException ex) {
            digest = "";
        } catch (NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
    }

    public static boolean checkResponseVnpay(IpnUrlRequest ipnUrlRequest, String hashSecret, String hashSecretTest) throws ApplicationException {
        try {
            Map fields = new HashMap();
            for (Field field : ipnUrlRequest.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String fieldValue = (String) field.get(ipnUrlRequest);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = ipnUrlRequest.getVnp_SecureHash();
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = PaymentUtils.hashAllFields(fields, hashSecret);
            log.info("signValue: " + signValue );
            String signValueTest = PaymentUtils.hashAllFields(fields, hashSecretTest);
            log.info("signValueTest: " + signValueTest);
            if (!signValue.equals(vnp_SecureHash) && !signValueTest.equals(vnp_SecureHash) ) {
                //Kiem tra chu ky OK
                throw new ApplicationException("ERR_0000308");
            }
            return true;
        } catch (ApplicationException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("ERR_0000308");
        }
    }

    //Util for VNPAY
    public static String hashAllFields(Map fields, String hashSecret) throws UnsupportedEncodingException {
        // create a list and sort it
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        // create a buffer for the md5 input and add the secure secret first
        StringBuilder sb = new StringBuilder();
        sb.append(hashSecret);
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(URLDecoder.decode(fieldValue,"UTF-8"));
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return Sha256VnPay(sb.toString());
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }
}
