package com.traderisk.user;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String fullName,
        String roleName,
        Boolean isActive,
        LocalDateTime createdAt
) {
    public static UserResponse from(AppUser user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole().getName(),
                user.getActive(),
                user.getCreatedAt()
        );
    }
}