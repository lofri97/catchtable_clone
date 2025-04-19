package com.lofri.catchtable.domain.restaurant.repository;

import com.lofri.catchtable.domain.restaurant.dto.RestaurantSearchCondition;
import com.lofri.catchtable.domain.restaurant.entity.QRestaurant;
import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import com.lofri.catchtable.domain.user.entity.QUserBookmark;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomRestaurantRepositoryImpl implements CustomRestaurantRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Restaurant> find(RestaurantSearchCondition cond, Pageable pageable) {
        QRestaurant r = QRestaurant.restaurant;
        BooleanBuilder builder = searchCondition(cond, r);

        List<Restaurant> content = queryFactory
                .selectFrom(r)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(r.count())
                .from(r)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    public Page<Restaurant> find(Long userId, RestaurantSearchCondition cond, Pageable pageable) {
        QRestaurant r = QRestaurant.restaurant;
        QUserBookmark ub = QUserBookmark.userBookmark;

        BooleanBuilder builder = searchCondition(cond, r);

        Expression<Boolean> bookmarkedExpr = ub.user.id.isNotNull().as("bookmarked");

        List<Tuple> tuples = queryFactory
                .select(r, bookmarkedExpr)
                .from(r)
                .leftJoin(ub)
                .on(ub.restaurant.eq(r)
                        .and(ub.user.id.eq(userId)))
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(r.count())
                .from(r)
                .where(builder);

        List<Restaurant> content = tuples.stream()
                .map(t -> {
                    Restaurant rest = t.get(r);
                    assert rest != null;
                    rest.setBookmarked(Boolean.TRUE.equals(t.get(bookmarkedExpr)));
                    return rest;
                })
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder searchCondition(RestaurantSearchCondition cond, QRestaurant r) {
        BooleanBuilder builder = new BooleanBuilder();
        if (cond.region() != null && !cond.region().isEmpty()) {
            builder.and(r.regionValue.in(cond.region()));
        }
        if (cond.foodType() != null && !cond.foodType().isEmpty()) {
            builder.and(r.foodTypes.any().in(cond.foodType()));
        }

        return builder;
    }

    @Override
    public Page<Restaurant> findBookmarked(Long userId, Pageable pageable) {
        QUserBookmark ub = QUserBookmark.userBookmark;
        QRestaurant r = QRestaurant.restaurant;

        List<Restaurant> content = queryFactory
                .select(r)
                .from(ub)
                .join(ub.restaurant, r)
                .where(ub.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ub.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(r.count())
                .from(ub)
                .join(ub.restaurant, r)
                .where(ub.user.id.eq(userId));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

    }
}
