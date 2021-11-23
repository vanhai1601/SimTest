package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.upload.FileRequest;
import com.hitex.yousim.dto.response.file.FileResponse;
import com.hitex.yousim.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Log4j2
public class FileServiceImpl implements FileService {
    @Override
    public FileResponse uploadFileImg(FileRequest fileRequest) throws IOException {
        File filePermission = null;
        FileResponse fileResponse = new FileResponse();
        try {
            String pathFile = fileRequest.getFileName().replaceAll(" ", "_") + ".png";
            String locationPath = Constant.IMAGE_DIRECTION + pathFile;
            String locationUrl = Constant.PATH + pathFile;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fd = simpleDateFormat.format(new Date());
            byte[] fileByte;
            fileByte = Base64.getDecoder().decode(fileRequest.getFileData());
            filePermission = new File(locationPath);
            if (filePermission.exists()) {
                pathFile = fileRequest.getFileName().replaceAll(" ", "_") + "_" + fd + ".png";
                locationPath = Constant.IMAGE_DIRECTION + pathFile;
                locationUrl = Constant.PATH + pathFile;
                filePermission = new File(locationPath);
            }
            FileOutputStream fileOutputStream = FileUtils.openOutputStream(filePermission);
            IOUtils.write(fileByte, fileOutputStream);
            IOUtils.closeQuietly(fileOutputStream);
            fileResponse.setFileName(pathFile);
            fileResponse.setUrl(locationUrl);
        } catch (IOException e) {
            log.error("Error"+e);
        } finally {
//            setPermission(filePermission);
        }
        return fileResponse;
    }

    public File setPermission(File file) throws IOException {
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
//        perms.add(PosixFilePermission.OWNER_WRITE);

        perms.add(PosixFilePermission.OTHERS_READ);
//        perms.add(PosixFilePermission.OTHERS_WRITE);

        perms.add(PosixFilePermission.GROUP_READ);

        Files.setPosixFilePermissions(file.toPath(), perms);
        return file;
    }
}