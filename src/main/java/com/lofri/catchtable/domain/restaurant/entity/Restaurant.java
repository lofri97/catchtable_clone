package com.lofri.catchtable.domain.restaurant.entity;

import com.lofri.catchtable.common.converter.LongListJsonConverter;
import com.lofri.catchtable.common.converter.StringListJsonConverter;
import com.lofri.catchtable.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Restaurant extends BaseEntity {

    @Id
    private Long id;
    private String name;
    private String description;

    @Convert(converter = StringListJsonConverter.class)
    @Column(name = "food_types", columnDefinition = "json")
    private List<String> foodTypes;
    private String regionValue;
    private String addr1;
    private String addr2;
    private String addrDescription;

    @Convert(converter = LongListJsonConverter.class)
    @Column(name = "addr_description_image_ids", columnDefinition = "json")
    private List<Long> addrDescriptionImageIds;
    private String contact;
    private String homepage;
//    private List<Long> imageIds;
    private Integer tablesUsageHour;
    private String refundPolicy;

    @Transient
    private Boolean bookmarked;

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
