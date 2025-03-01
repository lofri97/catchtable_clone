package com.lofri.catchtable.domain.restaurant.dto.menu;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuInfo {
    private MenuCategoryDetail category;
    private List<SimpleMenuDetail> menus;
}
