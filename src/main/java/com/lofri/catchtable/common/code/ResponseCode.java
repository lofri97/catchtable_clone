package com.lofri.catchtable.common.code;

import com.lofri.catchtable.domain.user.exception.DuplicateEmailException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // Success
    C000_000("Success",
            HttpStatus.OK,
            null),

    // Common
    C100_000("Invalid request",
            HttpStatus.BAD_REQUEST,
            new Class[] {
            HttpMediaTypeNotSupportedException.class,
            MethodArgumentNotValidException.class}),

    // User
    C200_000("Duplicated email",
            HttpStatus.BAD_REQUEST,
            new Class[]{
            DuplicateEmailException.class}),

    // Internal
    C500_000("Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR,
            null);


    private final String message;
    private final HttpStatus httpStatus;
    private final Class<? extends Exception>[] exceptions;
}
