package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantAnnouncements {
    private List<Announcement> announcements;
    private Pagination pagination;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Announcement {
        private String title;
        private String content;
        private String type;
        private LocalDateTime dateTime;
    }
}
