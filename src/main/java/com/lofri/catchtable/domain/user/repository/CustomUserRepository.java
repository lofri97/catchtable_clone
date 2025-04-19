package com.lofri.catchtable.domain.user.repository;

import com.lofri.catchtable.domain.user.entity.User;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findByIdContainsFollowCnt(Long id);
}
