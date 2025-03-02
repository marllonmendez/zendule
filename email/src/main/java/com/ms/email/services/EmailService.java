package com.ms.email.services;

import com.ms.email.dtos.EmailDTO;
import com.ms.email.entities.EmailEntity;
import com.ms.email.enums.StatusEmail;
import com.ms.email.mappers.EmailMapper;
import com.ms.email.repositories.EmailRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(EmailDTO emailDTO) {
        System.out.println("EmailDTO recebido: " + emailDTO);

        EmailEntity emailEntity = emailMapper.toEntity(emailDTO);
        System.out.println("EmailEntity após mapeamento: " + emailEntity);

        try {
            emailEntity.setSendDate(LocalDateTime.now());
            emailEntity.setEmailFrom(emailFrom);
            emailEntity.setStatusEmail(StatusEmail.PENDING);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getText());
            javaMailSender.send(message);

            emailEntity.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailEntity.setStatusEmail(StatusEmail.ERROR);
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        } finally {
            emailMapper.toDTO(
                emailRepository.save(emailEntity)
            );
        }
    }

}
