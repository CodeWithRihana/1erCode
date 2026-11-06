package com.example.Taskboard.Mapper;

import com.example.Taskboard.Dto.BoardRequestDto;
import com.example.Taskboard.Dto.BoardResponseDto;

import com.example.Taskboard.Model.Board;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mapper pour transformer les objets Board en DTOs et inversement.
 */
@Component
public class BoardMapper {

    /**
     * Convertit un DTO de creation en entite Board.
     */
    public Board toEntity(BoardRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setCreatedAt(LocalDateTime.now());
        return board;
    }

    /**
     * Convertit une entite Board en DTO de reponse.
     */
    public BoardResponseDto toResponseDto(Board entity) {
        if (entity == null) {
            return null;
        }
        return BoardResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .ownerId(entity.getOwner() != null ? entity.getOwner().getId() : null)
                .ownerName(entity.getOwner() != null ? entity.getOwner().getName() : null)
                .build();
    }
}
