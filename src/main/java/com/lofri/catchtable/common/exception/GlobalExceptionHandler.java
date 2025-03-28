package com.lofri.catchtable.common.exception;

import com.lofri.catchtable.common.code.ResponseCode;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseCodeResolver resolver;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseTemplate<Void>> handleException(Exception ex) {
        ResponseCode responseCode = resolver.resolve(ex);

        if (responseCode.equals(ResponseCode.C500_000)) {
            log.error("Unhandled exception", ex);
        }

        return new ResponseEntity<>(ResponseTemplate.of(responseCode), responseCode.getHttpStatus());
    }
}
