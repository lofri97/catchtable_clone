package com.lofri.catchtable.domain.restaurant.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lofri.catchtable.common.dto.image.ImageDetail;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleMenuDetail {
    private long id;
    private String name;
    private String description;
    private int price;
    private String category;
    private ImageDetail image;
    private List<String> menuTypes;
}
