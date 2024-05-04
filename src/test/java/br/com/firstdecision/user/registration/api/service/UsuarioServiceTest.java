package br.com.firstdecision.user.registration.api.service;

import br.com.firstdecision.user.registration.api.builder.UsuarioDTOBuilder;
import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.model.Usuario;
import br.com.firstdecision.user.registration.api.repository.UsuarioRepository;
import br.com.firstdecision.user.registration.api.service.exception.UsuarioRegistradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void whenUsuarioInformedThenItShouldBeCreated() throws UsuarioRegistradoException {

        ModelMapper modelMapper = new ModelMapper();

        UsuarioDTO expectedUsuarioDTO = UsuarioDTOBuilder.builder().build().toUsuarioDTO();
        Usuario expectedSavedUsuario = modelMapper.map(expectedUsuarioDTO, Usuario.class);
        assertNotNull(expectedSavedUsuario);
        lenient().when(usuarioRepository.findByNome(expectedUsuarioDTO.getNome())).thenReturn(Optional.empty());
        when(usuarioRepository.save(expectedSavedUsuario)).thenReturn(expectedSavedUsuario);

        UsuarioDTO createdUsuarioDTO = usuarioService.cadastrar(expectedUsuarioDTO);
        assertNotNull(createdUsuarioDTO);

        assertThat(createdUsuarioDTO.getNome(), is(equalTo(expectedUsuarioDTO.getNome())));
        assertThat(createdUsuarioDTO.getEmail(), is(equalTo(expectedUsuarioDTO.getEmail())));
        assertThat(createdUsuarioDTO.getSenha(), is(equalTo(expectedUsuarioDTO.getSenha())));
        assertThat(createdUsuarioDTO.getConfirmacaoSenha(), is(equalTo(expectedUsuarioDTO.getConfirmacaoSenha())));

    }

}
