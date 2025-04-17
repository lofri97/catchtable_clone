package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.code.GenderType;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;

    private String email;

    private String nickname;

    private String description;

    private GenderType gender;

    private String contact;

    private Boolean contactVerified;

    private String image;

    private Long followingCnt;

    private Long followerCnt;

    public UserDto(Long id,
                   String email,
                   String nickname,
                   String description,
                   GenderType gender,
                   String contact,
                   Boolean contactVerified,
                   String image,
                   Long followingCnt,
                   Long followerCnt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.description = description;
        this.gender = gender;
        this.contact = contact;
        this.contactVerified = contactVerified;
        this.image = image;
        this.followingCnt = followingCnt;
        this.followerCnt = followerCnt;
    }
}
