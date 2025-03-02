package com.ms.user.controllers;

import com.ms.user.dtos.UserRequestDTO;
import com.ms.user.dtos.UserResponseDTO;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    // Criar findAllUsers - Gean Ribeiro
    @GetMapping("/all")
    public ResponseEntity<Page<UserResponseDTO>> findAllUsers(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    // Criar updateUser - David Matheus
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.updateUser(userRequestDTO, userId));
    }

    @DeleteMapping("deactivate/{userId}")
    public ResponseEntity<UserResponseDTO> deactivateUser(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.deactivateUser(userId));
    }

}
