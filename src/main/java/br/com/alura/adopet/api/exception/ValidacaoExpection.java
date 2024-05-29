package br.com.alura.adopet.api.exception;

public class ValidacaoExpection extends RuntimeException {
    public ValidacaoExpection(String message) {
        super(message);
    }
}
