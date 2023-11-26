package com.codepred.cleanrepo.auth.exception;

public class LockedAccountException extends GenericAuthenticationException {

    public LockedAccountException() {
        super("User account is locked");
    }
}
