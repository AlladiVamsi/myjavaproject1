package com.API.Athuntication.Profile.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.Athuntication.Profile.Service.AuthService;
import com.API.Athuntication.Profile.dto.BuyerRegistrationRequest;
import com.API.Athuntication.Profile.dto.SendOtpRequest;
import com.API.Athuntication.Profile.dto.VerifyOtpRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/send-otp")
    public Object sendOtp(
            @RequestBody SendOtpRequest request) {

        return authService.sendOtp(request);
    }

    @PostMapping("/verify-otp")
    public Object verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        return authService.verifyOtp(request);
    }

    @PostMapping("/register-buyer")
    public Object registerBuyer(
            @RequestBody BuyerRegistrationRequest request,
            Authentication authentication) {

        return authService.registerBuyer(
                request,
                authentication
        );
    }
}