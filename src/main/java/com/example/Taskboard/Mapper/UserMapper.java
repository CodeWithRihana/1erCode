package com.example.Taskboard.Mapper;

import com.example.Taskboard.Dto.UserRequestDto;
import com.example.Taskboard.Dto.UserResponseDto;
import com.example.Taskboard.Model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper pour transformer les objets User en DTOs et inversement.
 * Composant Spring injectable grace a @Component.
 */
@Component
public class UserMapper {

    /**
     * Convertit un DTO de creation en entite User.
     */
    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    /**
     * Convertit une entite User en DTO de reponse.
     */
    public UserResponseDto toResponseDto(User entity) {
        if (entity == null) {
            return null;
        }
        return UserResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
