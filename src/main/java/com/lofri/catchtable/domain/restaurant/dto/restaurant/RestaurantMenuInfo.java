package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.lofri.catchtable.domain.restaurant.dto.menu.MenuInfo;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantMenuInfo {
    private List<MenuInfo> menuInfos;
    private LocalDate lastModifiedAt;
}
