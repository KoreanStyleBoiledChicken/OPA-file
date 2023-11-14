package com.sdev.opa.file.common.exception;

import com.sdev.opa.file.common.enums.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiAuthException extends RuntimeException {
    private final ResponseCode responseCode;
}

