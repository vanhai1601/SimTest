package com.hitex.yousim.dto.response.export;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

@Data
public class ExportListOrderRespone implements IResponseData {

    private String fileName;
    private String linkExport;

}
