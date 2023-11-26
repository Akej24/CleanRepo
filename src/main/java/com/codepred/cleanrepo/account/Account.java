package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.command.RegisterCommand;
import com.codepred.cleanrepo.account.value_object.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Valid @Embedded
    private UserEmail email;

    @Valid @Embedded
    private EncodedPassword encodedPassword;

    @Valid @Embedded
    private FirstName firstName;

    @Valid @Embedded
    private LastName lastName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must not be null")
    private UserRole role;

    @Valid @Embedded
    private Locked locked;

    public Account(RegisterCommand registerCommand, String encodedPassword) {
        email = registerCommand.getEmail();
        this.encodedPassword = new EncodedPassword(encodedPassword);
        firstName = registerCommand.getFirstName();
        lastName = registerCommand.getLastName();
        role = UserRole.USER;
        locked = new Locked(false);
    }
}
