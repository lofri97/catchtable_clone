package com.lofri.catchtable.domain.menu.dto;


import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMenuResponse {
    private long id;
    private List<String> images;
    private String name;
    private String description;
    private int price;
    private String type;
}
