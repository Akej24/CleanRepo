package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.command.RegisterCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountMicroTest {

    @Test
    @DisplayName("Newly created account should not be locked by default")
    void createFromValidData() {
        RegisterCommand registerCommand = new RegisterCommand.Json(
                "test@gmail.com",
                "Password123!",
                "Jack",
                "Smith").toCommand();
        var newAccount = new Account(registerCommand, "$10$XGXlxiUEQAUv9c");
        assertThat(newAccount.getLocked().isLocked()).isFalse();
    }
}