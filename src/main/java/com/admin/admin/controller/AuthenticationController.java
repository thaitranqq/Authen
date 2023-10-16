package com.admin.admin.controller;

import com.admin.admin.auth.AuthenticationRequest;
import com.admin.admin.auth.ForgotPasswordRequest;
import com.admin.admin.auth.RegisterRequest;
import com.admin.admin.service.AuthenticationService;
import com.nimbusds.oauth2.sdk.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)throws GeneralException, ParseException {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> login(@RequestBody RegisterRequest registerRequest)throws GeneralException, ParseException {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/forgot-password")
    public String forgotpassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest)throws GeneralException, ParseException {
        return authenticationService.forgotpassword(forgotPasswordRequest.getEmail(), forgotPasswordRequest.getPhone());
    }
}
