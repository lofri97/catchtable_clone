package com.lofri.catchtable.domain.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMenuReviewDetail {
    private String title;
    private String description;
    private String channel;
    private String writer;
    private String imgSrc;
    private LocalDate date;
}
