package com.lofri.catchtable.domain.user.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyProfileSnsInfoResponse {
    private String instagram;
    private String x;
    private String youtube;
    private String blog;
}
