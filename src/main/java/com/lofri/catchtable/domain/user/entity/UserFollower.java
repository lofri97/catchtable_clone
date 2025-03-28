package com.lofri.catchtable.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserFollower {

    @EmbeddedId
    private PrimaryKey pk;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    public static class PrimaryKey implements Serializable {
        @Column
        private Long userId;

        @Column
        private Long followerId;
    }
}
