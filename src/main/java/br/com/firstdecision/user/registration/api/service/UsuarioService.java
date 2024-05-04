package br.com.firstdecision.user.registration.api.service;

import br.com.firstdecision.user.registration.api.dto.UsuarioDTO;
import br.com.firstdecision.user.registration.api.model.Usuario;
import br.com.firstdecision.user.registration.api.repository.UsuarioRepository;
import br.com.firstdecision.user.registration.api.service.exception.UsuarioRegistradoException;
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

        verificarExistenciaUsuarioPorNome(usuario);

        usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioDTO.class);
    }

    private void verificarExistenciaUsuarioPorNome(Usuario usuario)  {
        String nome = usuario.getNome();

        usuarioRepository.findByNome(nome)
                .ifPresent(usuarioExistente -> {
                    if (!usuarioExistente.equals(usuario)) {
                        throw new UsuarioRegistradoException(String.format("Já existe um usuario cadastrado com o nome %s", nome));
                    }
                });
    }
}
