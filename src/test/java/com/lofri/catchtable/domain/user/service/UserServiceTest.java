package com.lofri.catchtable.domain.user.service;

import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.domain.user.entity.User;
import com.lofri.catchtable.domain.user.exception.DuplicateEmailException;
import com.lofri.catchtable.domain.user.exception.DuplicateNicknameException;
import com.lofri.catchtable.domain.user.exception.UserNotFoundException;
import com.lofri.catchtable.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
    class UpdateUser {
        Long id = 1L;

        @Nested
        class Success {
            String afterNickname = "TestNickname";
            String afterDescription = "TestDescription";
            String afterRegion = "TestRegion";
            String beforeNickname = "BeforeNickname";
            String beforeDescription = "BeforeDescription";
            String beforeRegion = "BeforeRegion";

            @Test
            @DisplayName("Update nickname")
            void success001() {
                // given
                User user = User.builder()
                        .nickname(beforeNickname)
                        .build();

                ReflectionTestUtils.setField(user, "id", id);
                ReflectionTestUtils.setField(user, "nickname", beforeNickname);
                ReflectionTestUtils.setField(user, "description", beforeDescription);
                ReflectionTestUtils.setField(user, "region", beforeRegion);

                given(userRepository.findById(id)).willReturn(Optional.of(user));

                // when
                userService.updateUser(id, afterNickname, null, null);

                // then
                assertThat(user)
                        .extracting(User::getNickname, User::getDescription, User::getRegion)
                        .containsExactly(afterNickname, beforeDescription, beforeRegion);
            }

            @Test
            @DisplayName("Update same nickname")
            void success004() {
                // given
                User user = User.builder()
                        .nickname(beforeNickname)
                        .build();

                ReflectionTestUtils.setField(user, "id", id);
                ReflectionTestUtils.setField(user, "nickname", beforeNickname);
                ReflectionTestUtils.setField(user, "description", beforeDescription);
                ReflectionTestUtils.setField(user, "region", beforeRegion);

                given(userRepository.findById(id)).willReturn(Optional.of(user));

                // when
                userService.updateUser(id, beforeNickname, null, null);

                // then
                assertThat(user)
                        .extracting(User::getNickname, User::getDescription, User::getRegion)
                        .containsExactly(beforeNickname, beforeDescription, beforeRegion);

            }

            @Test
            @DisplayName("Update description")
            void success002() {
                // given
                User user = User.builder()
                        .nickname(beforeNickname)
                        .build();

                ReflectionTestUtils.setField(user, "id", id);
                ReflectionTestUtils.setField(user, "nickname", beforeNickname);
                ReflectionTestUtils.setField(user, "description", beforeDescription);
                ReflectionTestUtils.setField(user, "region", beforeRegion);

                given(userRepository.findById(id)).willReturn(Optional.of(user));

                // when
                userService.updateUser(id, null, afterDescription, null);

                // then
                assertThat(user)
                        .extracting(User::getNickname, User::getDescription, User::getRegion)
                        .containsExactly(beforeNickname, afterDescription, beforeRegion);
            }

            @Test
            @DisplayName("Update region")
            void success003() {
                // given
                User user = User.builder()
                        .nickname(beforeNickname)
                        .build();

                ReflectionTestUtils.setField(user, "id", id);
                ReflectionTestUtils.setField(user, "nickname", beforeNickname);
                ReflectionTestUtils.setField(user, "description", beforeDescription);
                ReflectionTestUtils.setField(user, "region", beforeRegion);

                given(userRepository.findById(id)).willReturn(Optional.of(user));

                // when
                userService.updateUser(id, null, null, afterRegion);

                // then
                assertThat(user)
                        .extracting(User::getNickname, User::getDescription, User::getRegion)
                        .containsExactly(beforeNickname, beforeDescription, afterRegion);
            }
        }

        @Nested
        class Fail {
            Long id = 1L;

            @Test
            @DisplayName("User not found")
            void fail001() {
                // given
                given(userRepository.findById(id)).willReturn(Optional.empty());

                // when && then
                assertThatThrownBy(() -> userService.updateUser(id, null, null, null))
                        .isInstanceOf(UserNotFoundException.class);
            }

            @Test
            @DisplayName("Duplicate nickname")
            void fail002() {
                // given
                String beforeNickname = "TestNickname";
                String afterNickname = "AfterNickname";
                given(userRepository.findById(id)).willReturn(Optional.of(User.builder().nickname(beforeNickname).build()));
                given(userRepository.existsByNickname(afterNickname)).willReturn(true);

                // when && then
                assertThatThrownBy(() -> userService.updateUser(id, afterNickname, null, null))
                        .isInstanceOf(DuplicateNicknameException.class);
            }
        }
    }
}