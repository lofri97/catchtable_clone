package com.lofri.catchtable.domain.user.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleFollowDetail {
    protected int followerCnt;
    protected int followingCnt;
}
