package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmenityDetail {
    private String name;
    private String content;
    private String priceDetail;
    private String checkDetail;
}
