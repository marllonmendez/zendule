package com.ms.email.mappers;

import com.ms.email.dtos.EmailDTO;
import com.ms.email.entities.EmailEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "emailTo", target = "emailTo")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "text", target = "text")
    EmailEntity toEntity(EmailDTO emailDTO);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "emailTo", target = "emailTo")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "text", target = "text")
    EmailDTO toDTO(EmailEntity emailEntity);

}
