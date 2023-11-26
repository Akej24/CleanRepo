package com.codepred.cleanrepo.auth;

import com.codepred.cleanrepo.account.value_object.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "account")
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
class AccountCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Valid @Embedded
    private UserEmail email;

    @Valid @Embedded
    private EncodedPassword encodedPassword;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must not be null")
    private UserRole role;

    @Valid @Embedded
    private Locked locked;

    boolean isAccountLocked() {
        return this.locked.isLocked();
    }
}
