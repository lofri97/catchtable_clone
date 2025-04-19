package com.lofri.catchtable.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "페이징 정보")
public class Pagination {

    private long total;
    private int perPage;
    private int currentPage;
    private int totalPages;
    private List<OrderBy> orders;

    public static Pagination of(Page<?> page) {
        return builder()
                .total(page.getTotalElements())
                .perPage(page.getSize())
                .currentPage(page.getNumber())
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OrderBy {
        private String value;
        private String type;
    }
}
