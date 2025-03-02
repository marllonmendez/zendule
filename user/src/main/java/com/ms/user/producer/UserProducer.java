package com.ms.user.producer;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.dtos.UserResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserResponseDTO userResponseDTO) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(userResponseDTO.userId());
        emailDTO.setEmailTo(userResponseDTO.email());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText("Boas-vindas " + userResponseDTO.name() + " \nAgradecemos pela preferência!");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }

}
