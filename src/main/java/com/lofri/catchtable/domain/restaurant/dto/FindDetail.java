package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.dto.ImageDetail;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindDetail {
    private ImageDetail image;
    private String description;
}
