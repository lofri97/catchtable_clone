package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyCollectionsResponse {
    private List<Collections> collections;
    private Pagination pagination;

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
        private boolean isPublic;
    }
}
