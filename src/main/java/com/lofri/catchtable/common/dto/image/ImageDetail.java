package com.lofri.catchtable.common.dto.image;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageDetail {
    private String src;
    private String type;
}
