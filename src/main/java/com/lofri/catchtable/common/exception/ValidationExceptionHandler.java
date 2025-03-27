package com.lofri.catchtable.common.exception;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class ValidationExceptionHandler {

    private final ResponseCodeResolver responseCodeResolver;

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseTemplate<Map<String, List<MediaType>>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return ResponseTemplate.of(responseCodeResolver.resolve(ex), Map.of("supportedMediaTypes", ex.getSupportedMediaTypes()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseTemplate<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(fieldName, msg);
        });
        return ResponseTemplate.of(responseCodeResolver.resolve(ex), errors);
    }
}
