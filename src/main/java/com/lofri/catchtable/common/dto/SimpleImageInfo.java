package com.lofri.catchtable.common.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleImageInfo {
    private int totalCount;
    private List<ImageDetail> images;
}
