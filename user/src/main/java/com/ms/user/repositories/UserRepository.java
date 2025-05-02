package com.ms.user.repositories;

import com.ms.user.entities.UserEntity;
import com.ms.user.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserId(UUID userId);
    Page<UserEntity> findAllByUserStatus(UserStatus userStatus, Pageable pageable);
}
