package com.lofri.catchtable.domain.user.repository;

import com.lofri.catchtable.domain.user.dto.UserDto;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<UserDto> findByIdContainsFollowCnt(Long id);
}
