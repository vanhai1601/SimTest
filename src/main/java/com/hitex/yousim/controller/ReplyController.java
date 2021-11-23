package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.reply.ReplyRequest;
import com.hitex.yousim.dto.response.reply.ListReplyReponse;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.service.ReplyService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ReplyController extends BaseController{
    @Autowired
    private ReplyService replyService;

    @PostMapping("/listReply")
    public ResponseEntity<?> getListReply(@RequestBody BaseRequestData<ReplyRequest> baseRequestData) throws ApplicationException{
        try {
            ListReplyReponse listReplyReponse = replyService.getListReply(baseRequestData);
            return success(listReplyReponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
