package com.example.Taskboard.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size ;
import jakarta.validation.constraints.Email;


@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder 
public class UserRequestDto {
    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom de l'utilisateur doit contenir entre 2 et 50 caracteres" )
    private String name;

    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Email(message = "L'email de l'utilisateur n'est pas valide")
    private String email;

    @NotBlank(message= "Le mot de passe est obligatoir")
    private String password; 
}