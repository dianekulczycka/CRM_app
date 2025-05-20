package org.example.crmdemo.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private Boolean isActive;
    private LocalDateTime lastLogin;
}
