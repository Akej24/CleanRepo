package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.command.RegisterCommand;
import com.codepred.cleanrepo.account.exception.EmailTakenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountCommandHandler {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    void handleRegisterAccount(RegisterCommand registerCommand) {
        Optional<Account> alreadyRegisteredAccount = accountRepository.findByEmail_Email(registerCommand.getEmail().getEmail());
        if(alreadyRegisteredAccount.isPresent()){
            log.warn("Unsuccessfully registered - email must be not taken");
            throw new EmailTakenException();
        }
        String encodedPassword = bCryptPasswordEncoder.encode(registerCommand.getPassword().getRawPassword());
        accountRepository.save(new Account(registerCommand, encodedPassword));
        log.info("Successfully registered");
    }
}
