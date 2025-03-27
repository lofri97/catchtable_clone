package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.code.GenderType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Nullable
    private String nickname;

    @NotBlank
    private String contact;

    @NotNull
    private GenderType gender;
}
