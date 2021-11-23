package com.hitex.yousim.dto.request.reply;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Reply;
import com.hitex.yousim.model.view.ViewReply;
import lombok.Data;

@Data
public class ReplyRequest extends ViewReply implements IRequestData {
    private int pageSize;
    private int page;

    @Override
    public boolean isValid() {
        return false;
    }
}
