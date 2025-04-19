package com.lofri.catchtable.domain.restaurant.repository;

import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, CustomRestaurantRepository {
}
