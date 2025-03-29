package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.code.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invalid contact")
    private String contact;

    @NotNull
    private GenderType gender;
}
