package com.codepred.cleanrepo.account.value_object;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class FirstName implements Serializable {

    @NotNull(message = "First name cannot be blank")
    private String firstName;

}
