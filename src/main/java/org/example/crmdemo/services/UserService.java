package org.example.crmdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.crmdemo.dto.user.SignUpRequestDto;
import org.example.crmdemo.dto.user.SignUpResponseDto;
import org.example.crmdemo.entities.User;
import org.example.crmdemo.enums.Role;
import org.example.crmdemo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto createUser(@Valid SignUpRequestDto signUpRequestDto) {
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(password);
        user.setRole(Role.ROLE_MANAGER);
        userRepository.save(user);

        return SignUpResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with provided email was not found"));
    }
}
