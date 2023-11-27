package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.command.RegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/account")
@RequiredArgsConstructor
class AccountController {

    private final AccountCommandHandler accountCommandHandler;

    @PostMapping
    ResponseEntity<Void> registerAccount(@RequestBody RegisterCommand.Json requestJson) {
        var registerCommand = requestJson.toCommand();
        accountCommandHandler.handleRegisterAccount(registerCommand);
        return ResponseEntity.ok().build();
    }
}
