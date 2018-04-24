package com.pigutu.app.exception;

import javax.security.sasl.AuthenticationException;

public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}