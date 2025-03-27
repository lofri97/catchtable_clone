package com.lofri.catchtable.domain.review.dto;

import com.lofri.catchtable.common.code.RateType;
import com.lofri.catchtable.common.code.TimeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewRequest {
    @NotNull
    private Long restaurantId;

    @NotNull
    private LocalDate visitDate;

    @NotNull
    private TimeType visitHour;

    @NotBlank
    private String content;
    private List<String> hashtags;

    @NotNull
    private List<RateInfo> rates;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RateInfo {

        @NotNull
        private RateType type;

        @Min(1) @Max(5)
        private Integer rate;
    }
}
