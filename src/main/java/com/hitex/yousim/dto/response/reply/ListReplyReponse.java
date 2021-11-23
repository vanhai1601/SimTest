package com.hitex.yousim.dto.response.reply;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListReplyReponse implements IResponseData {
    private int totalPage;
    private int totalItem;
    List<ReplyReponse> replyReponseList;
}
