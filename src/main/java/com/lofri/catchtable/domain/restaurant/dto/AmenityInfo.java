package com.lofri.catchtable.domain.restaurant.dto;

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
