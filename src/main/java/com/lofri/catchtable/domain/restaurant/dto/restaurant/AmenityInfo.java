package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.lofri.catchtable.domain.restaurant.dto.restaurant.AmenityDetail;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AmenityInfo {
    private List<String> amenities;
    private List<AmenityDetail> amenityDetails;
}
