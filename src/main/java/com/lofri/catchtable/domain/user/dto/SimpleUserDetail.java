package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.dto.ImageDetail;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleUserDetail {
    protected long id;
    protected String name;
    protected ImageDetail image;
}
