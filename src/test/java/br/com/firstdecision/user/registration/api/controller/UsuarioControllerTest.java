package br.com.firstdecision.user.registration.api.controller;

import br.com.firstdecision.user.registration.api.builder.UsuarioDTOBuilder;
import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.firstdecision.user.registration.api.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    private static final String USUARIO_API_URL_PATH = "http://localhost:8080/usuario";

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAUsuarioIsCreated() throws Exception {

        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.builder().build().toUsuarioDTO();

        when(usuarioService.cadastrar(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post(USUARIO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuarioDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(usuarioDTO.getNome())))
                .andExpect(jsonPath("$.email", is(usuarioDTO.getEmail())))
                .andExpect(jsonPath("$.senha", is(usuarioDTO.getSenha())))
                .andExpect(jsonPath("$.confirmacaoSenha", is(usuarioDTO.getConfirmacaoSenha())));


    }
}
