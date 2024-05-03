package br.com.firstdecision.user.registration.api.service.exception;

public class UsuarioNotFoundException extends Exception{

    public UsuarioNotFoundException(String nome) {
        super(String.format("Usuário com nome %s não foi encontrado.", nome ));
    }
}
