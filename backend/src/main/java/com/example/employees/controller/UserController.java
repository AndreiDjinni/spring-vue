package com.example.employees.controller;

import com.example.employees.dto.auth.SignUpDto;
import com.example.employees.entity.User;
import com.example.employees.exception.exceptions.BadRequestException;
import com.example.employees.security.CurrentUser;
import com.example.employees.security.UserPrincipal;
import com.example.employees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.findById(userPrincipal.getId());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto dto) {

        if(userService.userExist(dto.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        userService.create(dto);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
