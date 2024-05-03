package br.com.firstdecision.user.registration.api.service.exception;

public class UsuarioRegistradoException extends Exception {

    public UsuarioRegistradoException(String nome) {
        super(String.format("Usuário com nome %s já está cadastrado.", nome));
    }
}
