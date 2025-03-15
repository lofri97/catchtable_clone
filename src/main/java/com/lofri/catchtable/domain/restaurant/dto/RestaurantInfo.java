package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.dto.ImageDetail;
import com.lofri.catchtable.common.dto.SimpleImageInfo;
import com.lofri.catchtable.domain.review.dto.SimpleReviewInfo;
import com.lofri.catchtable.domain.menu.SimpleMenuInfo;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantInfo {
    private long id;
    private String name;
    private String simpleDescription;
    private String description;
    private String contact;
    private String homepage;
    private List<ImageDetail> restaurantImages;

    private String announcement;

    private AddressDetail addressInfo;
    private AmenityInfo amenityInfo;
    private List<WorkHourDetail> workHourInfo;

    private SimpleMenuInfo menuInfo;
    private SimpleImageInfo imageInfo;
    private SimpleReviewInfo reviewInfo;
}
