package com.lofri.catchtable.domain.user.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMySnsInfoRequest {

    @Nullable
    private Boolean use;

    @Nullable
    @Length(max = 300)
    private String instagram;

    @Nullable
    @Length(max = 300)
    private String x;

    @Nullable
    @Length(max = 300)
    private String youtube;

    @Nullable
    @Length(max = 300)
    private String blog;
}
