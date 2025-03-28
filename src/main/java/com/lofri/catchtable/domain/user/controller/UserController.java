package com.lofri.catchtable.domain.user.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.user.dto.*;
import com.lofri.catchtable.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseTemplate<Void> createUser(@Valid @RequestBody CreateUserRequest request) {
        userService.createUser(
                request.getEmail(),
                request.getPassword(),
                request.getContact(),
                request.getGender()
        );
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseTemplate<GetUserResponse> getUser(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseTemplate<Void> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseTemplate<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseTemplate.ok();
    }

    @PostMapping("/{userId}/follow")
    public ResponseTemplate<Void> followUser(@PathVariable long userId) {
        return null;
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseTemplate<Void> unfollowUser(@PathVariable long userId) {
        return null;
    }

    @GetMapping("/{userId}/sns")
    public ResponseTemplate<GetMyProfileSnsInfoResponse> getUserSns(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}/sns")
    public ResponseTemplate<Void> updateUserSns(@Valid @RequestBody UpdateMySnsInfoRequest request) {
        return null;
    }

    @GetMapping("/{userId}/preference")
    public ResponseTemplate<GetMyProfilePreferenceResponse> getUserPreference(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}/preference")
    public ResponseTemplate<Void> updateUserPreference(@Valid @RequestBody UpdateMyProfilePreference request) {
        return null;
    }
}
