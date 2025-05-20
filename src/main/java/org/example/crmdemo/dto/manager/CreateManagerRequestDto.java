package org.example.crmdemo.dto.manager;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateManagerRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
