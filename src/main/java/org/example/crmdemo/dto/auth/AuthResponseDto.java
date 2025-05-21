package org.example.crmdemo.dto.auth;

import lombok.Builder;
import lombok.Data;
import org.example.crmdemo.enums.Role;

@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String name;
    private Role role;
}
