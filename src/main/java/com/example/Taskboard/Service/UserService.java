package com.example.Taskboard.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Taskboard.Dto.UserRequestDto;
import com.example.Taskboard.Dto.UserResponseDto;
import com.example.Taskboard.Mapper.UserMapper;
import com.example.Taskboard.Exception.DuplicateResourceException;
import com.example.Taskboard.Exception.ResourceNotFoundException;
import com.example.Taskboard.Model.User;
import com.example.Taskboard.Repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

/**
 * Couche Service : contient la LOGIQUE METIER.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // CREATE

    public UserResponseDto createUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Un utilisateur avec cet email existe deja : " + requestDto.getEmail());
        }

        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    // READ

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur introuvable avec id=" + id));
        return userMapper.toResponseDto(user);
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur introuvable avec id=" + id));
    }

    // UPDATE

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {

        // 1. Récupérer l'utilisateur existant (ou 404)
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur introuvable avec id=" + id));

        // 2. Vérifier l'email : seulement s'il a changé ET qu'il est déjà pris
        boolean emailHasChanged = !existingUser.getEmail().equals(requestDto.getEmail());
        if (emailHasChanged && userRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Un utilisateur avec cet email existe déjà : " + requestDto.getEmail());
        }

        // 3. Appliquer les modifications
        existingUser.setName(requestDto.getName());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        // 4. Sauvegarder (utilisation de l'instance userRepository)
        User updatedUser = userRepository.save(existingUser);
        
        // Utilisation de l'instance userMapper
        return userMapper.toResponseDto(updatedUser);
    }

    // DELETE

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Utilisateur introuvable avec id=" + id);
        }
        // Utilisation de l'instance userRepository
        userRepository.deleteById(id);
    }
}