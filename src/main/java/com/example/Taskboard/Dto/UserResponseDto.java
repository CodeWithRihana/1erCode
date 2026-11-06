package com.example.Taskboard.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UserResponseDto {
    private long id;
    private String name;
    private String email;
}