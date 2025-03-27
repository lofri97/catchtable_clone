package com.lofri.catchtable.domain.review.dto;


import com.lofri.catchtable.common.code.RateType;
import com.lofri.catchtable.common.code.TimeType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetReviewResponse {
    private Long id;
    private UserDetail user;
    private String content;
    private Float avgRate;
    private List<RateDetail> rates;
    private TimeType visitTime;
    private List<String> images;
    private Integer likeCnt;
    private Integer commentCnt;
    private LocalDateTime createdAt;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserDetail {
        private Long id;
        private String name;
        private String image;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RateDetail {
        private RateType type;
        private Integer rate;
    }
}