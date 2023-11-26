package com.codepred.cleanrepo.auth.exception;

public class InvalidJwtException extends GenericAuthenticationException{

    public InvalidJwtException() {
        super("Could not create or verify jwt");
    }
}
