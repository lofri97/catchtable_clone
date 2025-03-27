package com.lofri.catchtable.common.controller;

import com.lofri.catchtable.common.dto.LoginRequest;
import com.lofri.catchtable.common.dto.LoginResponse;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @PostMapping("/login")
    public ResponseTemplate<LoginResponse> login(@RequestBody LoginRequest request) {
        return null;
    }
}
