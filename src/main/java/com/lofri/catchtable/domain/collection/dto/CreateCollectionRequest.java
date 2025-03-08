package com.lofri.catchtable.domain.collection.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCollectionRequest {

    @NotEmpty
    @Length(max = 30)
    private String title;

    @Nullable
    @Length(max = 2000)
    private String description;

    private boolean isPublic;
}
