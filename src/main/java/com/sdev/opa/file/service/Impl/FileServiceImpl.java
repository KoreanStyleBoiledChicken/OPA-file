package com.sdev.opa.file.service.Impl;

import com.sdev.opa.file.common.enums.ResponseCode;
import com.sdev.opa.file.common.exception.ApiException;
import com.sdev.opa.file.service.FileService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private String check = "zip";
    private Charset CP866 = Charset.forName("CP866");
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    /**
     * @param path 파일 저장 root 경로
     *             파일 저장 root 경로 디렉토리 생성
     */
    @Override
    public void init(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new ApiException(ResponseCode.SERVER_CREATED_DIR_ERROR);
        }
    }

    /**
     * @param file 업로드된 파일
     *             uuid값을 통해 파일 별도의 디렉토리 이름 생성 -> 파일 저장 root 위치
     */
    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new ApiException(ResponseCode.DATA_MISSING);
            }

            String uuid = UUID.randomUUID().toString();
            Path root = Paths.get(uploadPath + "/" + uuid);

            if (!Files.exists(root)) {
                init(root);
            }

            storeFile(file, root);
        } catch (IOException e) {
            throw new ApiException(ResponseCode.FILE_WRONG_ERROR);
        } catch (IllegalArgumentException e) {
            throw new ApiException(ResponseCode.ZIP_ENCODING_ERROR);
        } catch (Exception e) {
            throw new ApiException(ResponseCode.SERVER_STORE_ERROR);
        }
    }

    /**
     * @param file      업로드된 파일
     * @param path      파일 저장 root 경로
     * @throws IOException file.getinputstream의 경우 IOException이 발생
     *                     파일에 대해 저장, zip파일 유무에 따라 unzip호출
     *                                         todo file을 db에 저장할 떄 저장위치에 관해서 수정해야함.
     */
    @Override
    public void storeFile(MultipartFile file, Path path) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }



}
