package com.lofri.catchtable.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleReviewInfo<T extends SimpleReviewDetail> {
    protected float avgRate;
    protected int totalCount;
    protected List<T> reviews;
}
