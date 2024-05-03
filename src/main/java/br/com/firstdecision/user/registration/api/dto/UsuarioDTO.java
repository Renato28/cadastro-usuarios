package br.com.firstdecision.user.registration.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    @NotBlank
    @Size(min = 3, max = 15, message = "O nome do usuário deve ter entre 3 e 15 caracteres")
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "A senha do usuário deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotBlank
    private String confirmacaoSenha;
}
