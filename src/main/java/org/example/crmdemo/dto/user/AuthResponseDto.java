package org.example.crmdemo.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
}
