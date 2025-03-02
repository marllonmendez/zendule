package com.ms.user.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.user.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID userId,
        String name,
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime created,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updated,
        UserStatus userStatus
) {}
