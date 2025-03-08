package com.lofri.catchtable.domain.restaurant.dto.review;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewInfo extends SimpleReviewInfo<ReviewDetail> {
    protected List<RateCount> rateCounts;
}
