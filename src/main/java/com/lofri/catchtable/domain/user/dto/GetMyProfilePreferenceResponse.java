package com.lofri.catchtable.domain.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyProfilePreferenceResponse {

    @NotNull
    private List<String> preferences;

    @NotNull
    @Min(1)
    private Integer minPrice;

    @NotNull
    @Max(40)
    private Integer maxPrice;
}
