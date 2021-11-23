package com.hitex.yousim.dto.request.question;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Question;

public class QuestionRequest extends Question implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
