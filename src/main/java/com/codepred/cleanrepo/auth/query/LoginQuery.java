package com.codepred.cleanrepo.auth.query;

import com.codepred.cleanrepo.account.value_object.UserEmail;
import com.codepred.cleanrepo.account.value_object.RawPassword;
import com.codepred.cleanrepo.common.SelfValidating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public final class LoginQuery extends SelfValidating<LoginQuery> {

    @Valid private final UserEmail email;
    @Valid private final RawPassword password;

    private LoginQuery(LoginQuery.Json json) {
        this.email = new UserEmail(json.email);
        this.password = new RawPassword(json.password);
        validateSelf();
    }

    public record Json(String email, String password) {
        public LoginQuery toQuery() {
            return new LoginQuery(this);
        }
    }
}
