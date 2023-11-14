package com.sdev.opa.file.controller;

import com.sdev.opa.file.common.dto.ResponseDto;
import com.sdev.opa.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/file")
    public ResponseDto<?> uploadFile(
            @RequestPart("file") MultipartFile file
    ) {
        fileService.store(file);
        return new ResponseDto<>(null);
    }

}
