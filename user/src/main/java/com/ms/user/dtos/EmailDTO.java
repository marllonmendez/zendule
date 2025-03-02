package com.ms.user.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailDTO {
        UUID userId;
        String emailTo;
        String subject;
        String text;
}
