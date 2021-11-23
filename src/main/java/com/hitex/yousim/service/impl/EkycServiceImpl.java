package com.hitex.yousim.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.service.EkycService;
import com.hitex.yousim.utils.DateUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

@Service
@Log4j2
public class EkycServiceImpl implements EkycService {
    @Override
    public CustomerResponse getInfoCardFont(MultipartFile file, String type, String typeDoc) throws ApplicationException {
        CustomerResponse customerResponse = null;
        try {
//            List<TypeDoc>
            String hashImgFont = getHashCodeFromFile(file);
            String checkTypeDoc = checkTypeDoc(hashImgFont);
            switch (type) {
                case Constant.TYPE_DOC.CMT.CMT:
                    if (!Constant.TYPE_DOC.CMT.CMT_FONT.equals(checkTypeDoc)) {
                        throw new ApplicationException(String.valueOf(ApplicationCode.CMT_INVALID));
                    }
                    break;
                case Constant.TYPE_DOC.CCCD.CCCD:
                    if (!Constant.TYPE_DOC.CCCD.CCCD.equals(checkTypeDoc)) {
                        throw new ApplicationException(String.valueOf(ApplicationCode.CCCD_INVALID));
                    }
                    break;
            }
            customerResponse = readInfoImgFont(hashImgFont, type);
            customerResponse.setHashImgFont(hashImgFont);
        } catch (ApplicationException e) {
            throw e;
        }
        return customerResponse;
    }

    private CustomerResponse readInfoImgFont(String img, String type) throws ApplicationException {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n  " +
                    "\"img_front\": \"" + img + "\"," +
                    "\r\n  \"client_session\": \"client_session\"," +
                    "\r\n  \"type\": \"" + type + "\"," +
                    "\r\n  \"validate_postcode\": true," +
                    "\r\n  \"token\": \"token\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.idg.vnpt.vn/ai/v1/ocr/id/front")
                    .method("POST", body)
                    .addHeader("Token-id", "c3e97265-603e-a3f1-e053-604fc10a5b9a")
                    .addHeader("Token-key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjsO3kfw+KRe4TxFFp5y4wOABALRz/4QA1BznzJZvV0yh34g5V+JAZqhMbTzieCKCf6rybXnmqDR5Y90isscb8CAwEAAQ==")
                    .addHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicWxzcGljQGlkZy52bnB0LnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJxbHNwaWNAaWRnLnZucHQudm4iLCJ1dWlkX2FjY291bnQiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjM4ZWEzYjk4LTA0OWEtNDZlMS1hODEwLWYxMjQ3MTIxNzk1MSIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.jiZcwj6X2FDH6CpqCMFxHBnxj2ysuVeHK4SnndZNmdWn5Ltf2sJIrI87LadPdnZ8z_2aIds2Z5sjyzVPZyKyvcXlMoE50Xo66PzocFjoxQnOyB3KuKCxGgZl1kvE8kOThHcAk4o87aD7FsLAU0c3qk75F-bmGvsXDLIoIuNmuRNWaXT8kO-SWdHFntvgG-88c0eQpYz7Da9SHfgqBKJ37g0QyAd99zxe-0ay-KsCr9H-b9lhN1j-iVALQ2R-xH-8QgbpceacYD0mUJtvSGSTQCn5_sxwGmGihvqS-Asyy0rPHkEdUySygBVmRn_pMScj76SHZpb2fAXDfqIhjXMjTA")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject rsObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonPrimitive statusCode = rsObject.get("statusCode").getAsJsonPrimitive();
            JsonObject object = rsObject.get("object").getAsJsonObject();
            log.info("=========================object===================== " + object);
            if (!"200".equals(statusCode.getAsString())) {
                throw new ApplicationException(String.valueOf(ApplicationCode.ERROR_CALL_EKYC));
            }
            JsonArray post_code = object.getAsJsonArray("post_code").getAsJsonArray();
            log.info("=========================post_code===================== " + post_code);
            JsonObject objectAddress;
            String ward = "";
            String city = "";
            String district = "";
            String recent_location = "";
            try {
                objectAddress = post_code.get(1).getAsJsonObject();
                ward = objectAddress.get("ward").getAsJsonArray().get(0).getAsString();
                city = objectAddress.get("city").getAsJsonArray().get(0).getAsString();
                district = objectAddress.get("district").getAsJsonArray().get(0).getAsString();
                recent_location = object.getAsJsonPrimitive("recent_location").getAsString().split("\n")[0]
                        + ", " + object.getAsJsonPrimitive("recent_location").getAsString().split("\n")[1];
            } catch (Exception e) {
                throw e;
            }
            customerResponse.setIdCard(object.get("id").getAsString());
            customerResponse.setSex(object.get("gender").getAsString().equals("Nam") ? Constant.MALE : Constant.FEMALE);
            customerResponse.setCusName(object.get("name").getAsString());
            customerResponse.setCityId(city);
            customerResponse.setDistrictId(district);
            customerResponse.setVillageId(ward);
            customerResponse.setAddress(recent_location);
            customerResponse.setDateOfBirth(DateUtils.convertStringToDate(object.getAsJsonPrimitive("birth_day").getAsString(), "dd/MM/yyyy"));
        } catch (ParseException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (ApplicationException e) {
            log.error(e);
            throw e;
        }
        return customerResponse;
    }

    private String checkTypeDoc(String img) throws ApplicationException {
        String type = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, "{\r\n    " +
                    "\"img_card\": \"" + img + "\"," +
                    "\r\n    \"client_session\": \"client_session\"," +
                    "\r\n    \"token\": \"token\"" +
                    "\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.idg.vnpt.vn/ai/v1/classify/id")
                    .method("POST", requestBody)
                    .addHeader("Token-id", "c3e97265-603e-a3f1-e053-604fc10a5b9a")
                    .addHeader("Token-key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjsO3kfw+KRe4TxFFp5y4wOABALRz/4QA1BznzJZvV0yh34g5V+JAZqhMbTzieCKCf6rybXnmqDR5Y90isscb8CAwEAAQ==")
                    .addHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicWxzcGljQGlkZy52bnB0LnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJxbHNwaWNAaWRnLnZucHQudm4iLCJ1dWlkX2FjY291bnQiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjM4ZWEzYjk4LTA0OWEtNDZlMS1hODEwLWYxMjQ3MTIxNzk1MSIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.jiZcwj6X2FDH6CpqCMFxHBnxj2ysuVeHK4SnndZNmdWn5Ltf2sJIrI87LadPdnZ8z_2aIds2Z5sjyzVPZyKyvcXlMoE50Xo66PzocFjoxQnOyB3KuKCxGgZl1kvE8kOThHcAk4o87aD7FsLAU0c3qk75F-bmGvsXDLIoIuNmuRNWaXT8kO-SWdHFntvgG-88c0eQpYz7Da9SHfgqBKJ37g0QyAd99zxe-0ay-KsCr9H-b9lhN1j-iVALQ2R-xH-8QgbpceacYD0mUJtvSGSTQCn5_sxwGmGihvqS-Asyy0rPHkEdUySygBVmRn_pMScj76SHZpb2fAXDfqIhjXMjTA")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject convertObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonPrimitive statusCode = convertObject.getAsJsonPrimitive("statusCode");
            log.info("========================Status Code =================== " + statusCode);
            if (!"200".equals(statusCode.getAsString())) {
                throw new ApplicationException(String.valueOf(ApplicationCode.ERROR_CALL_EKYC), statusCode.getAsString());
            }
            JsonObject object = convertObject.getAsJsonObject("object");
            log.info("========================Object =================== " + object);
            JsonPrimitive typeDoc = object.getAsJsonPrimitive("type");
            log.info("========================typeDoc =================== " + typeDoc);
            type = typeDoc.getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

    private String getHashCodeFromFile(MultipartFile file) {
        String hashCodeImg = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            File convertFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convertFile);
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getOriginalFilename(),
                            RequestBody.create(MediaType.parse("application/octet-stream"),
                                    convertFile))
                    .addFormDataPart("title", "")
                    .addFormDataPart("description", "")
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.idg.vnpt.vn/file-service/v1/addFile")
                    .method("POST", body)
                    .addHeader("Token-id", "c3e97265-603e-a3f1-e053-604fc10a5b9a")
                    .addHeader("Token-key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjsO3kfw+KRe4TxFFp5y4wOABALRz/4QA1BznzJZvV0yh34g5V+JAZqhMbTzieCKCf6rybXnmqDR5Y90isscb8CAwEAAQ==")
                    .addHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicWxzcGljQGlkZy52bnB0LnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJxbHNwaWNAaWRnLnZucHQudm4iLCJ1dWlkX2FjY291bnQiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjM4ZWEzYjk4LTA0OWEtNDZlMS1hODEwLWYxMjQ3MTIxNzk1MSIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.jiZcwj6X2FDH6CpqCMFxHBnxj2ysuVeHK4SnndZNmdWn5Ltf2sJIrI87LadPdnZ8z_2aIds2Z5sjyzVPZyKyvcXlMoE50Xo66PzocFjoxQnOyB3KuKCxGgZl1kvE8kOThHcAk4o87aD7FsLAU0c3qk75F-bmGvsXDLIoIuNmuRNWaXT8kO-SWdHFntvgG-88c0eQpYz7Da9SHfgqBKJ37g0QyAd99zxe-0ay-KsCr9H-b9lhN1j-iVALQ2R-xH-8QgbpceacYD0mUJtvSGSTQCn5_sxwGmGihvqS-Asyy0rPHkEdUySygBVmRn_pMScj76SHZpb2fAXDfqIhjXMjTA")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject convertedObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonPrimitive hashImg = convertedObject.get("object").getAsJsonObject().get("hash").getAsJsonPrimitive();
            log.info("========================Hash img upload ====================" + hashImg);
            hashCodeImg = hashImg.getAsString();
        } catch (Exception e) {
            log.error(e);
        }
        return hashCodeImg;
    }


    @Override
    public CustomerResponse getInfoImgBack(MultipartFile file, String type, String typeDoc) throws ApplicationException, IOException, ParseException {
        CustomerResponse customerResponse = null;
        try {
            String hashImgBack = getHashCodeFromFile(file);
            String checkTypeDoc = checkTypeDoc(hashImgBack);
            switch (type){
                case Constant.TYPE_DOC.CMT.CMT :
                    if(!Constant.TYPE_DOC.CMT.CMT_BACK.equals(checkTypeDoc)){
                        throw new ApplicationException(String.valueOf(ApplicationCode.CMT_INVALID));
                    }
                    break;
                case Constant.TYPE_DOC.CCCD.CCCD :
                    if(!Constant.TYPE_DOC.CCCD.CCCD_BACK.equals(checkTypeDoc)){
                        throw new ApplicationException(String.valueOf(ApplicationCode.CCCD_INVALID));
                    }
                    break;
            }
            customerResponse = readInfoCardBack(hashImgBack, typeDoc);
        } catch (ApplicationException | IOException e) {
            log.error(e);
            throw e;
        }
        return customerResponse;
    }

    @Override
    public int getInfoAvatar(MultipartFile file, String imgFont) throws IOException, ApplicationException {
        try {
            String hashImgAvatar = getHashCodeFromFile(file);
            compareFace(imgFont,hashImgAvatar);
        }catch (ApplicationException e){
            e.printStackTrace();
            throw e;
        }
        return ApplicationCode.FACE_MATCH;
    }

    private void compareFace(String imgFont, String imgFace) throws ApplicationException {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{" +
                    "\r\n  \"img_front\": \""+imgFont+"\"," +
                    "\r\n  \"img_face\" : \""+imgFace+"\"," +
                    "\r\n  \"client_session\": \"client_session\"," +
                    "\r\n  \"token\": \"token\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.idg.vnpt.vn/ai/v1/face/compare")
                    .method("POST", body)
                    .addHeader("Token-id", "c3e97265-603e-a3f1-e053-604fc10a5b9a")
                    .addHeader("Token-key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjsO3kfw+KRe4TxFFp5y4wOABALRz/4QA1BznzJZvV0yh34g5V+JAZqhMbTzieCKCf6rybXnmqDR5Y90isscb8CAwEAAQ==")
                    .addHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicWxzcGljQGlkZy52bnB0LnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJxbHNwaWNAaWRnLnZucHQudm4iLCJ1dWlkX2FjY291bnQiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjM4ZWEzYjk4LTA0OWEtNDZlMS1hODEwLWYxMjQ3MTIxNzk1MSIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.jiZcwj6X2FDH6CpqCMFxHBnxj2ysuVeHK4SnndZNmdWn5Ltf2sJIrI87LadPdnZ8z_2aIds2Z5sjyzVPZyKyvcXlMoE50Xo66PzocFjoxQnOyB3KuKCxGgZl1kvE8kOThHcAk4o87aD7FsLAU0c3qk75F-bmGvsXDLIoIuNmuRNWaXT8kO-SWdHFntvgG-88c0eQpYz7Da9SHfgqBKJ37g0QyAd99zxe-0ay-KsCr9H-b9lhN1j-iVALQ2R-xH-8QgbpceacYD0mUJtvSGSTQCn5_sxwGmGihvqS-Asyy0rPHkEdUySygBVmRn_pMScj76SHZpb2fAXDfqIhjXMjTA")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject rsObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonPrimitive statusCode = rsObject.get("statusCode").getAsJsonPrimitive();
            JsonObject object = rsObject.get("object").getAsJsonObject();
            JsonPrimitive msg = object.get("msg").getAsJsonPrimitive();
            if(!statusCode.getAsString().equals("200")){
                throw new ApplicationException(String.valueOf(ApplicationCode.NOT_FIND_FACE));
            }
            if(statusCode.getAsString().equals("200") && msg.getAsString().equals("NOMATCH")){
                throw new ApplicationException(String.valueOf(ApplicationCode.FACE_NOT_MATCH));
            }
        }catch (ApplicationException e){
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CustomerResponse readInfoCardBack(String img, String type) throws ApplicationException, IOException, ParseException {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n  " +
                    "\"img_back\": \"" + img + "\"," +
                    "\r\n  \"client_session\": \"client_session\"," +
                    "\r\n  \"type\": \"" + type + "\"," +
                    "\r\n  \"token\": \"token\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.idg.vnpt.vn/ai/v1/ocr/id/back")
                    .method("POST", body)
                    .addHeader("Token-id", "c3e97265-603e-a3f1-e053-604fc10a5b9a")
                    .addHeader("Token-key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjsO3kfw+KRe4TxFFp5y4wOABALRz/4QA1BznzJZvV0yh34g5V+JAZqhMbTzieCKCf6rybXnmqDR5Y90isscb8CAwEAAQ==")
                    .addHeader("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicWxzcGljQGlkZy52bnB0LnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJxbHNwaWNAaWRnLnZucHQudm4iLCJ1dWlkX2FjY291bnQiOiI0N2M5YTU3Mi03Njg0LTExZWItOGUyZi0yNTQ5ZDNmNGY0YzIiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjM4ZWEzYjk4LTA0OWEtNDZlMS1hODEwLWYxMjQ3MTIxNzk1MSIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.jiZcwj6X2FDH6CpqCMFxHBnxj2ysuVeHK4SnndZNmdWn5Ltf2sJIrI87LadPdnZ8z_2aIds2Z5sjyzVPZyKyvcXlMoE50Xo66PzocFjoxQnOyB3KuKCxGgZl1kvE8kOThHcAk4o87aD7FsLAU0c3qk75F-bmGvsXDLIoIuNmuRNWaXT8kO-SWdHFntvgG-88c0eQpYz7Da9SHfgqBKJ37g0QyAd99zxe-0ay-KsCr9H-b9lhN1j-iVALQ2R-xH-8QgbpceacYD0mUJtvSGSTQCn5_sxwGmGihvqS-Asyy0rPHkEdUySygBVmRn_pMScj76SHZpb2fAXDfqIhjXMjTA")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject rsObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonPrimitive statusCode = rsObject.get("statusCode").getAsJsonPrimitive();
            if(!statusCode.getAsString().equals("200")){
                throw new ApplicationException(String.valueOf(ApplicationCode.DOC_INVALID));
            }
            JsonObject object = rsObject.getAsJsonObject("object");
            String issueDate = object.getAsJsonPrimitive("issue_date").getAsString();
            String issuePlace = object.getAsJsonPrimitive("issue_place").getAsString();
            customerResponse.setDateIssueCard(DateUtils.convertStringToDate(issueDate,"dd/MM/yyyy"));
            if (issuePlace.equals("CỤC TRƯỞNG CỤC CẢNH SÁT ĐKQL CƯ TRÚ VÀ DLQG VỀ DÂN CƯ")) {
                issuePlace = "Cục ĐKQL cư trú và DLQG về dân cư";
            } else if (issuePlace.contains("QUẢN LÝ HÀNH CHÍNH")) {
                issuePlace = "Cục Quản lý HCTTXH";
            }
            customerResponse.setPlaceIssueCard(issuePlace);
        } catch (ApplicationException | IOException | ParseException e) {
            log.error(e);
            throw e;
        }
        return customerResponse;
    }
}

