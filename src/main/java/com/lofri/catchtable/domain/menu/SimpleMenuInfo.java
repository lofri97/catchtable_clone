package com.lofri.catchtable.domain.menu;

import com.lofri.catchtable.common.dto.ImageDetail;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleMenuInfo {
    private ImageDetail menuBoardImage;
    private List<SimpleMenuDetail> menus;
}
