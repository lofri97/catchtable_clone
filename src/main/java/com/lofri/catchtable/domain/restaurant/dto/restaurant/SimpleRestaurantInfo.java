package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.lofri.catchtable.domain.reservation.SimpleAvailReserveDetail;
import com.lofri.catchtable.domain.menu.SimpleRateDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleRestaurantInfo {
    private long id;
    private String name;
    private boolean bookmarked;
    private String address;
    private List<String> foodTypes;
    private SimpleRateDetail rateInfo;
    private SimpleWorkHourDetail workHour;
    private List<SimplePriceDetail> priceInfos;
    private List<SimpleAvailReserveDetail> reserveInfos;
}
