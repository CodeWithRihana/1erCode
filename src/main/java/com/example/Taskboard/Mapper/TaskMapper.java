package com.example.Taskboard.Mapper;

import com.example.Taskboard.Dto.TaskUpdateRequestDto;
import com.example.Taskboard.Dto.TaskResponseDto;

import com.example.Taskboard.Model.Task;
import com.example.Taskboard.Model.TaskStatus;
import org.springframework.stereotype.Component;

/**
 * Mapper pour transformer les objets Task en DTOs et inversement.
 */
@Component
public class TaskMapper {

    /**
     * Convertit un DTO de creation en entite Task (statut initial A_FAIRE).
     */
    public Task toEntity(TaskUpdateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(TaskStatus.A_FAIRE);
        return task;
    }

    /**
     * Met a jour une entite existante a partir d'un DTO de mise a jour.
     */
    public void updateEntityFromDTO(Task entity, TaskUpdateRequestDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
    }

    /**
     * Convertit une entite Task en DTO de reponse.
     */
    public TaskResponseDto toResponseDto(Task entity) {
        if (entity == null) {
            return null;
        }
        return TaskResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .boardId(entity.getBoard() != null ? entity.getBoard().getId() : null)
                .build();
    }
}
