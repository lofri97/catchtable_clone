package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.lofri.catchtable.common.dto.image.ImageDetail;
import com.lofri.catchtable.common.dto.image.SimpleImageInfo;
import com.lofri.catchtable.domain.restaurant.dto.review.SimpleReviewInfo;
import com.lofri.catchtable.domain.restaurant.dto.menu.SimpleMenuInfo;
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
