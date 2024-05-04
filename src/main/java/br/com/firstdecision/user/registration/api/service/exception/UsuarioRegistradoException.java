package br.com.firstdecision.user.registration.api.service.exception;

public class UsuarioRegistradoException extends RuntimeException {

    public UsuarioRegistradoException(String mensagem) {
        super(mensagem);
    }
}
