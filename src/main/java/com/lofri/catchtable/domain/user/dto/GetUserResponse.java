package com.lofri.catchtable.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserResponse {
    private long id;
    private String image;
    private String name;
    private String description;
    private Long followerCnt;
    private Long followingCnt;
    private Float avgRate;
    private String region;
    private List<String> preferences;

    public static GetUserResponse of(UserDto user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getNickname())
                .description(user.getDescription())
                .followingCnt(user.getFollowingCnt())
                .followerCnt(user.getFollowerCnt())
                // Todo avgRate 추가
                // Todo user.getRegion 추가
                // Todo preference 추가
                .build();
    }
}
