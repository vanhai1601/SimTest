package com.hitex.yousim.dto.response.file;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

@Data
public class FileResponse implements IResponseData {
    String fileName;
    String url;
}
