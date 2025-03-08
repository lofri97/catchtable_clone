package com.lofri.catchtable.domain.user.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserCollectionsResponse {
    private List<Collections> collections;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Collections {
        private long id;
        private String title;
        private String image;
        private int itemCnt;
        private int subscribedCnt;
    }
}
