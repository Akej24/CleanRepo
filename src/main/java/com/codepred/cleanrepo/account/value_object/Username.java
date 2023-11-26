package com.codepred.cleanrepo.account.value_object;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class Username implements Serializable {

    @NotBlank(message="Username must not be blank")
    private String username;

}
