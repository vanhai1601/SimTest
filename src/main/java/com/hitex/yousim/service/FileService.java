package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.upload.FileRequest;
import com.hitex.yousim.dto.response.file.FileResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

import java.io.IOException;

public interface FileService {
    FileResponse uploadFileImg(FileRequest fileRequest) throws IOException;
}
