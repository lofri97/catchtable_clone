package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDetail {
    private String address1;
    private String address2;
    private FindDetail findDetail;
}
