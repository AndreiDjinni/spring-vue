package com.example.employees.service;

import com.example.employees.dto.auth.SignUpDto;
import com.example.employees.entity.User;
import com.example.employees.entity.enums.AuthProvider;
import com.example.employees.exception.exceptions.ResourceNotFoundException;
import com.example.employees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean userExist(String email) {

        return userRepository.existsByEmail(email);
    }

    public void create(SignUpDto dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProvider(AuthProvider.LOCAL);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
