package com.lofri.catchtable.common.exception;

import com.lofri.catchtable.common.code.ResponseCode;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseCodeResolver resolver;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseTemplate<Void>> handleException(Exception ex) {
        ResponseCode responseCode = resolver.resolve(ex);
        return new ResponseEntity<>(ResponseTemplate.of(responseCode), responseCode.getHttpStatus());
    }
}
