package com.ms.user.mappers;

import com.ms.user.dtos.UserRequestDTO;
import com.ms.user.dtos.UserResponseDTO;
import com.ms.user.entities.UserEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    UserEntity toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toDTO(UserEntity userEntity);

}
