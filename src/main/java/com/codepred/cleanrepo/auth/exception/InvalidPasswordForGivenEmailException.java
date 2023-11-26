package com.codepred.cleanrepo.auth.exception;

public class InvalidPasswordForGivenEmailException extends GenericAuthenticationException {

    public InvalidPasswordForGivenEmailException() {
        super("Invalid password for given e-mail");
    }
}
