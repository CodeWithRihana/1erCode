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
public class LoginRequestDto {
    
    @NotBlank (message = "L'email de l'utilisateur est obligatoire")
    @Email (message = "L'email de l'utilisateur n'est pas valide")
    private String email;
    @NotBlank (message = "Le mot de passe de l'utilisateur est obligatoire")
    @Size (min = 6, max = 100, message = "Le mot de passe doit contenir entre 6 et 100 caractères")
    private String password;

   
}