package com.lofri.catchtable.domain.user.entity;

import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBookmark {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    private LocalDateTime createdAt;
}