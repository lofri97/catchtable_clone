package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.lofri.catchtable.common.dto.image.ImageDetail;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindDetail {
    private ImageDetail image;
    private String description;
}
