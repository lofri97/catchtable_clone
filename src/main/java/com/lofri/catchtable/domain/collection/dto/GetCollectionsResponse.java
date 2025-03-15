package com.lofri.catchtable.domain.collection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCollectionsResponse {
        private List<Collections> collections;
        private Pagination pagination;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Collections {
                private long id;
                private String title;
                private String image;
                private int itemCnt;
                private int subscribedCnt;
                private Boolean isPublic;
        }
}
