package com.lofri.catchtable.domain.user.repository;

import com.lofri.catchtable.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
