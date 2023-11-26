package com.codepred.cleanrepo.account.command;

import com.codepred.cleanrepo.account.value_object.FirstName;
import com.codepred.cleanrepo.account.value_object.LastName;
import com.codepred.cleanrepo.account.value_object.RawPassword;
import com.codepred.cleanrepo.account.value_object.UserEmail;
import com.codepred.cleanrepo.common.SelfValidating;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public final class RegisterCommand extends SelfValidating<RegisterCommand> {

    @Valid private final UserEmail email;
    @Valid private final RawPassword password;
    @Valid private final FirstName firstName;
    @Valid private final LastName lastName;

    private RegisterCommand(RegisterCommand.Json json) {
        this.email = new UserEmail(json.email);
        this.password = new RawPassword(json.password);
        this.firstName = new FirstName(json.firstName);
        this.lastName = new LastName(json.lastName);
        validateSelf();
    }

    public record Json(String email, String password, String firstName, String lastName) {
        public RegisterCommand toCommand() {
            return new RegisterCommand(this);
        }
    }
}
