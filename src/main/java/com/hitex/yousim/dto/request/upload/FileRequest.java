package com.hitex.yousim.dto.request.upload;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 12/04/2021 - 6:15 PM
 */
@Data
public class FileRequest implements IRequestData {
    private String fileName;
    private String fileData;

    @Override
    public boolean isValid() {
        return false;
    }
}
