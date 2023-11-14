package com.sdev.opa.file.common.dto;

import com.sdev.opa.file.common.enums.ResponseCode;
import com.sdev.opa.file.common.exception.ApiAuthException;
import com.sdev.opa.file.common.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Builder
@Getter
public class ResponseDto<T> {
    private final Boolean success;
    private final T data;
    private final ExceptionDto error;

    public ResponseDto(@Nullable T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }

    public static ResponseEntity<Object> toException(ApiException e) {
        return ResponseEntity.status(e.getResponseCode().getHttpStatus())
                .body(ResponseDto.builder()
                        .success(false)
                        .data(null)
                        .error(new ExceptionDto(e.getResponseCode()))
                        .build());
    }

    // JWT, 인가 관련 ExceptionDto
    public static ResponseEntity<Object> toException(ApiAuthException e) {
        return ResponseEntity.status(e.getResponseCode().getHttpStatus())
                .body(ResponseDto.builder()
                        .success(false)
                        .data(null)
                        .error(new ExceptionDto(e.getResponseCode()))
                        .build());
    }

    public static ResponseEntity<Object> toException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.builder()
                        .success(false)
                        .data(null)
                        .error(new ExceptionDto(ResponseCode.SERVER_ERROR))
                        .build());
    }
}