package br.com.firstdecision.user.registration.api.builder;

import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import lombok.Builder;

@Builder
public class UsuarioDTOBuilder {

    @Builder.Default
    private String nome = "testeusuario";

    @Builder.Default
    private String email = "testeusuario@gmail.com";

    @Builder.Default
    private String senha = "123456";

    @Builder.Default
    private String confirmacaoSenha = "123456";

    public UsuarioDTO toUsuarioDTO() {
        return new UsuarioDTO(nome, email, senha, confirmacaoSenha);
    }


}
