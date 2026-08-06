package com.traderisk.user;

public record CreateUserRequest(
        String email,
        String fullName,
        String passwordHash,
        String roleName
) {
}