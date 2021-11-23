package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.reply.ListReplyReponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ReplyService {
    ListReplyReponse getListReply(BaseRequestData baseRequestData) throws ApplicationException;
}
