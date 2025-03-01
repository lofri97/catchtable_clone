package com.lofri.catchtable.domain.restaurant.dto.menu;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuCategoryDetail {
    private String name;
    private String description;
    private int minPrice;
    private int maxPrice;
}
