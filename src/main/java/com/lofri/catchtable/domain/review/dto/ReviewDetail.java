package com.lofri.catchtable.domain.restaurant.dto.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lofri.catchtable.common.dto.image.ImageDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDetail extends SimpleReviewDetail {
    private String visitTime;
    private List<RateDetail> rateDetails;
    private List<ImageDetail> images;
    private int likeCount;
    private int commentCount;
}
