package br.com.firstdecision.user.registration.api.controller;

import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO cadastrar(@Valid @RequestBody UsuarioDTO dto) {

        return usuarioService.cadastrar(dto);
    }
}
