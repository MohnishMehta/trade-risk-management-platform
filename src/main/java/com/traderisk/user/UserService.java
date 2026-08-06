package com.traderisk.user;

import com.traderisk.role.Role;
import com.traderisk.role.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    public UserService(AppUserRepository appUserRepository, RoleRepository roleRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (appUserRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Role role = roleRepository.findByName(request.roleName())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + request.roleName()));

        AppUser user = new AppUser(
                request.email(),
                request.fullName(),
                request.passwordHash(),
                role
        );

        AppUser savedUser = appUserRepository.save(user);

        return UserResponse.from(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        return UserResponse.from(user);
    }
}