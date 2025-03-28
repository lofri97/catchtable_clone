package com.lofri.catchtable.domain.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UpdateUserRequest {

    @Nullable
    @NotBlank
    @Length(min = 2, max = 15)
    private String nickname;

    @Nullable
    @Length(max = 35)
    private String description;

    @Nullable
    @Length(max = 15)
    private String region;
}
