package br.com.firstdecision.user.registration.api.service;

import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.model.Usuario;
import br.com.firstdecision.user.registration.api.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO cadastrar(UsuarioDTO dto) {

        ModelMapper mapper = new ModelMapper();

        Usuario usuario = mapper.map(dto, Usuario.class);
        usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioDTO.class);
    }
}
