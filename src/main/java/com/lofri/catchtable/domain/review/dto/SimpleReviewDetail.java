package com.lofri.catchtable.domain.review.dto;

import com.lofri.catchtable.common.dto.ImageDetail;
import com.lofri.catchtable.domain.user.dto.SimpleUserDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleReviewDetail {
    protected long id;
    protected SimpleUserDetail userDetail;
    protected String description;
    protected float rate;
    protected ImageDetail image;
    protected LocalDate createdAt;
}
