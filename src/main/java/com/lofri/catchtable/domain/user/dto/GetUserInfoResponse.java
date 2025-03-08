package com.lofri.catchtable.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserInfoResponse {
    private long id;
    private String image;
    private String name;
    private int followerCnt;
    private int followingCnt;
    private float avgRate;
    private String region;
    private List<String> preferences;
    private Sns sns;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Sns {
        private String instagram;
        private String x;
        private String youtube;
        private String blog;
    }
}
