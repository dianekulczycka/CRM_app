package org.example.crmdemo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.crmdemo.dto.auth.AuthRequestDto;
import org.example.crmdemo.dto.auth.AuthResponseDto;
import org.example.crmdemo.dto.auth.RefreshTokenRequestDto;
import org.example.crmdemo.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> signIn(@RequestBody @Valid AuthRequestDto authRequestDto) {
        return new ResponseEntity<>(authService.authenticate(authRequestDto), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDto> getNewTokenPair(@RequestBody @Valid RefreshTokenRequestDto refreshTokenRequestDto) {
        return new ResponseEntity<>(authService.refreshToken(refreshTokenRequestDto.getRefreshToken()), HttpStatus.OK);
    }
}