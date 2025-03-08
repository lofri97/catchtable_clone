package com.lofri.catchtable.domain.menu;

import com.lofri.catchtable.common.dto.image.ImageDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class MenuDetail {
    private long id;
    private String name;
    private int price;
    private ImageDetail image;
    private String description;
    private List<SearchMenuReviewDetail> searchReviews;
}
