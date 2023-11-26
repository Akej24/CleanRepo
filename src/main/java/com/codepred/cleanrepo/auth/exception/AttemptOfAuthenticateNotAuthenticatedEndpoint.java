package com.codepred.cleanrepo.auth.exception;

public class AttemptOfAuthenticateNotAuthenticatedEndpoint extends GenericAuthenticationException{

    public AttemptOfAuthenticateNotAuthenticatedEndpoint() {
        super("Attempt of authenticate endpoint marked as not authenticated");
    }
}
