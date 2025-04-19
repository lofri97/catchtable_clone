package com.lofri.catchtable.domain.user.repository;

import com.lofri.catchtable.domain.user.entity.User;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.lofri.catchtable.common.entity.QImage.image;
import static com.lofri.catchtable.domain.user.entity.QUser.user;
import static com.lofri.catchtable.domain.user.entity.QUserFollower.userFollower;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<User> findByIdContainsFollowCnt(Long id) {
        return Optional.ofNullable(
                query.select(Projections.constructor(User.class,
                                user.id,
                                user.email,
                                user.nickname,
                                user.description,
                                user.gender,
                                user.contact,
                                user.contactVerified,
                                image,
                                ExpressionUtils.as(
                                        JPAExpressions.select(userFollower.pk.userId.count())
                                                .from(userFollower)
                                                .where(userFollower.pk.userId.eq(id)),
                                        "followerCnt"
                                ),
                                ExpressionUtils.as(
                                        JPAExpressions.select(userFollower.pk.followerId.count())
                                                .from(userFollower)
                                                .where(userFollower.pk.followerId.eq(id)),
                                        "followingCnt")
                        ))
                        .from(user)
                        .leftJoin(user.image, image)
                        .where(user.id.eq(id))
                        .fetchOne());
    }
}
