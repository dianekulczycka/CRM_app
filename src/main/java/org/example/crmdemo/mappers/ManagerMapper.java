package org.example.crmdemo.mappers;

import org.example.crmdemo.dto.manager.ManagerDto;
import org.example.crmdemo.entities.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    public static ManagerDto toDto(Manager manager) {
        if (manager == null) {
            return null;
        }

        return ManagerDto.builder()
                .id(manager.getId())
                .email(manager.getEmail())
                .name(manager.getName())
                .surname(manager.getSurname())
                .isActive(manager.getIsActive())
                .lastLogin(manager.getLastLogIn() != null ? manager.getLastLogIn() : null)
                .build();
    }
}
