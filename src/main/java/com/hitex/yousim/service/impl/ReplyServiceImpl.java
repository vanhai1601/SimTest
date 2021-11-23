package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.reply.ReplyRequest;
import com.hitex.yousim.dto.response.reply.ListReplyReponse;
import com.hitex.yousim.dto.response.reply.ReplyReponse;
import com.hitex.yousim.model.view.ViewReply;
import com.hitex.yousim.repository.ReplyRepository;
import com.hitex.yousim.service.ReplyService;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public ListReplyReponse getListReply(BaseRequestData baseRequestData) throws ApplicationException {
        ListReplyReponse listReplyReponse = new ListReplyReponse();
        ReplyRequest replyRequest = (ReplyRequest) baseRequestData.getWsRequest();

        try {
            List<ViewReply> viewReplyList = replyRepository.getListReply(String.valueOf(LocaleContextHolder.getLocale()),PageRequest.of(replyRequest.getPage(),replyRequest.getPageSize()));
            List<ReplyReponse> replyReponses = new ArrayList<>();

            for(ViewReply viewReply : viewReplyList) {
                ReplyReponse replyReponse = new ReplyReponse();
                BeanUtils.copyProperties(viewReply,replyReponse);
                replyReponses.add(replyReponse);
            }
            int totalItem = replyRepository.countListReply(String.valueOf(LocaleContextHolder.getLocale()));
            int totalPage = (int) Math.ceil((double) totalItem / (double) replyRequest.getPageSize());
            listReplyReponse.setReplyReponseList(replyReponses);
            listReplyReponse.setTotalItem(totalItem);
            listReplyReponse.setTotalPage(totalPage);
            return listReplyReponse;
        } catch (Exception e){
            throw new ApplicationException("error");
        }

    }
}
