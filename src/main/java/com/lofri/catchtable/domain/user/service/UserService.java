package com.lofri.catchtable.domain.user.service;

import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.domain.user.entity.User;
import com.lofri.catchtable.domain.user.exception.DuplicateEmailException;
import com.lofri.catchtable.domain.user.exception.DuplicateNicknameException;
import com.lofri.catchtable.domain.user.exception.UserNotFoundException;
import com.lofri.catchtable.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String email, String password, String contact, GenderType gender) {
        if (userRepository.existsByEmail(email)) throw new DuplicateEmailException(email);

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(UUID.randomUUID().toString().substring(0, 10))
                .contact(contact)
                .gender(gender)
                .build();

        userRepository.save(user);
    }

    public void updateUser(Long id, String nickname, String description, String region) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!StringUtils.equals(user.getNickname(), nickname) && userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(nickname);
        }
        user.updateNickname(nickname);
        user.updateDescription(description);
        user.updateRegion(region);
        userRepository.save(user);
    }
}
