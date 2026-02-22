package com.dkhoa.marketplace.controller;

import com.dkhoa.marketplace.dto.request.AuthenticationRequest;
import com.dkhoa.marketplace.dto.response.ApiResponse;
import com.dkhoa.marketplace.dto.response.AuthenticationResponse;
import com.dkhoa.marketplace.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticated(authenticationRequest))
                .build();
    }



}
