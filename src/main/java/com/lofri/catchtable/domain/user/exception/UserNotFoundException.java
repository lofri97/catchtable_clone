package com.lofri.catchtable.domain.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(id.toString());
    }
}
