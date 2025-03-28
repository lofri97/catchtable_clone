package com.lofri.catchtable.domain.user.service;

import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.domain.user.exception.DuplicateEmailException;
import com.lofri.catchtable.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserService userService = new UserService(userRepository, passwordEncoder);

    @Nested
    class CreateUser {

        @Nested
        class Success {

            @Test
            @DisplayName("Normal case")
            void success001() throws Exception {
                // given
                String email = "lofri97@gmail.com";
                String password = "password";
                String contact = "01090918849";
                GenderType gender = GenderType.MALE;

                given(userRepository.existsByEmail(email)).willReturn(false);

                // when & then
                assertThatCode(() -> userService.createUser(email, password, contact, gender)).doesNotThrowAnyException();
            }
        }

        @Nested
        class Fail {

            @Test
            @DisplayName("Duplicate email")
            void fail001() throws Exception {
                // given
                String email = "lofri97@gmail.com";
                String password = "password";
                String contact = "01090918849";
                GenderType gender = GenderType.MALE;
                given(userRepository.existsByEmail(email)).willReturn(true);

                // when & then
                assertThatThrownBy(() -> userService.createUser(email, password, contact, gender))
                        .isInstanceOf(DuplicateEmailException.class);

            }
        }

    }

    @Nested
    class DeleteUser {
        @Nested
        class Success {
            @Test
            @DisplayName("User exist")
            void success001() {
                // given
                given(userRepository.existsById(1L)).willReturn(true);

                // when && then
                assertThatCode(() -> userService.deleteUser(1L))
                        .doesNotThrowAnyException();
            }
        }

        @Nested
        class Fail {

            @Test
            @DisplayName("User not exist")
            void fail001() {
                // given
                given(userRepository.existsByEmail(any())).willReturn(false);

                // when && then
                assertThatThrownBy(() -> userService.deleteUser(1L))
                        .isInstanceOf(RuntimeException.class);
            }
        }
    }
}