package com.codepred.cleanrepo.account.exception;

public class EmailTakenException extends GenericAccountException{

    public EmailTakenException() {
        super("This e-mail is already taken");
    }

}
