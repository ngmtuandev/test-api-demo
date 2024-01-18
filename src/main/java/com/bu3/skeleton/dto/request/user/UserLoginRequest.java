package com.bu3.skeleton.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {

    @NotNull(message = "Hello Word!..")
    private String email;

    private String password;
}
