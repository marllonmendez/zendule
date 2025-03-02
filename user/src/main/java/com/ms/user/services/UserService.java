package com.ms.user.services;

import com.ms.user.dtos.UserRequestDTO;
import com.ms.user.dtos.UserResponseDTO;
import com.ms.user.entities.UserEntity;
import com.ms.user.enums.UserStatus;
import com.ms.user.exceptions.NotFoundException;
import com.ms.user.exceptions.ValidException;
import com.ms.user.mappers.UserMapper;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Função Lambda
        userRepository.findByEmail(userRequestDTO.email())
                .ifPresent(user -> {
                    throw new ValidException("User already exists");
                });

        var user = userMapper.toDTO(
                userRepository.save(
                        userMapper.toEntity(userRequestDTO)
                )
        );

        userProducer.publishMessageEmail(user);

        return user;
    }

    public UserResponseDTO findUser(UUID userId) {
        return userMapper.toDTO(
                userRepository.findByUserId(userId)
                        .orElseThrow(() -> new NotFoundException("User not found"))
        );
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, UUID userId) {
        UserResponseDTO userResponseDTO = findUser(userId);

        if (userResponseDTO.userStatus() == UserStatus.INACTIVE) {
            throw new ValidException("User is already inactive");
        }

        // Closure
        Function<UserEntity, UserEntity> updateUserFunction = user -> {
            user.setName(userRequestDTO.name());
            user.setUpdated(LocalDateTime.now());
            return userRepository.save(user);
        };

        // Função de alta ordem
        userRepository.findById(userId)
                .ifPresent(updateUserFunction::apply);

        return findUser(userId);
    }

    public UserResponseDTO deactivateUser(UUID userId) {
        UserResponseDTO userResponseDTO = findUser(userId);

        if (userResponseDTO.userStatus() == UserStatus.INACTIVE) {
            throw new ValidException("User is already inactive");
        }

        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.setUserStatus(UserStatus.INACTIVE);
                    userRepository.save(user);
                });

        return findUser(userId);
    }

}
