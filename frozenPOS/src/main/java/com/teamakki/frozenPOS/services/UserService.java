package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.User;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() { return userRepository.findAll(); }

    public Optional<User> getById(Long id) { return userRepository.findById(id); }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public User update(Long id, User updated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setUsername(updated.getUsername());
        if (updated.getPasswordHash() != null && !updated.getPasswordHash().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(updated.getPasswordHash()));
        }
        user.setRole(updated.getRole());
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found with id " + id);
        userRepository.deleteById(id);
    }
}
