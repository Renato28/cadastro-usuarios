package br.com.firstdecision.user.registration.api.service;

import br.com.firstdecision.user.registration.api.builder.UsuarioDTOBuilder;
import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.mapper.UsuarioMapper;
import br.com.firstdecision.user.registration.api.model.Usuario;
import br.com.firstdecision.user.registration.api.repository.UsuarioRepository;
import br.com.firstdecision.user.registration.api.service.exception.UsuarioRegistradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    private static final long INVALID_USUARIO_ID = 1L;

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void whenUsuarioInformedThenItShouldBeCreated() throws UsuarioRegistradoException {

        UsuarioDTO expectedUsuarioDTO = UsuarioDTOBuilder.builder().build().toUsuarioDTO();
        Usuario expectedSavedUsuario = usuarioMapper.toModel(expectedUsuarioDTO);

        when(usuarioRepository.findByNome(expectedUsuarioDTO.getNome())).thenReturn(Optional.empty());
        when(usuarioRepository.save(expectedSavedUsuario)).thenReturn(expectedSavedUsuario);

        UsuarioDTO createdUsuarioDTO = usuarioService.cadastrar(expectedUsuarioDTO);

        assertThat(createdUsuarioDTO.getNome(), is(equalTo(expectedUsuarioDTO.getNome())));
        assertThat(createdUsuarioDTO.getEmail(), is(equalTo(expectedUsuarioDTO.getEmail())));
        assertThat(createdUsuarioDTO.getSenha(), is(equalTo(expectedUsuarioDTO.getSenha())));
        assertThat(createdUsuarioDTO.getConfirmacaoSenha(), is(equalTo(expectedUsuarioDTO.getConfirmacaoSenha())));

    }

}
