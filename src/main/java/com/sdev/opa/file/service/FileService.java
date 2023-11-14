package com.sdev.opa.file.service;

import com.mongodb.connection.ServerType;
import com.sdev.opa.file.common.enums.ResponseCode;
import com.sdev.opa.file.common.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void init(Path path);

    //Local 저장
    void store(MultipartFile file);

    void storeFile(MultipartFile file, Path path) throws IOException;
}
