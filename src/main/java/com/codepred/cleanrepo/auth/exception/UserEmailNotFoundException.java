package com.codepred.cleanrepo.auth.exception;

public class UserEmailNotFoundException extends GenericAuthenticationException {

    public UserEmailNotFoundException() {
        super("User with given e-mail has not been found");
    }
}
