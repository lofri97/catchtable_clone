package com.lofri.catchtable.domain.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMenusResponse {
    private String boardImage;
    private List<MenuCategory> categories;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MenuCategory {
        private String name;
        private String description;
        private Integer minPrice;
        private Integer maxPrice;

        private List<MenuInfo> menus;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MenuInfo {
        private long id;
        private List<String> images;
        private String name;
        private String description;
        private int price;
        private String type;
    }
}
