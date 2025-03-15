package com.lofri.catchtable.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "페이징 정보")
public class Pagination {

    @Schema(description = "총 조회 데이터 수")
    private long total;

    @Schema(description = "페이지 당 데이터 수")
    private int perPage;

    @Schema(description = "현재 페이지")
    private int currentPage;

    @Schema(description = "총 페이지")
    private int totalPages;

    @Schema(description = "정렬 데이터 목록")
    private List<OrderBy> orders;

    @Schema(description = "데이터 필터링 목록")
    private List<Filter> filters;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OrderBy {
        @Schema(description = "값")
        private String value;

        @Schema(description = "종류")
        private String type;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Filter {
        private List<String> values;
        private String type;
        private Boolean isExclude;
    }
}
