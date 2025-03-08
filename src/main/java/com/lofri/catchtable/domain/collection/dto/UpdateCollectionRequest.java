package com.lofri.catchtable.domain.collection.dto;


import jakarta.annotation.Nullable;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCollectionRequest {


    @Nullable
    @Length(max = 30)
    private String title;

    @Nullable
    @Length(max = 2000)
    private String description;

    @Nullable
    private Boolean isPublic;
}
