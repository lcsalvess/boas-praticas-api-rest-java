package br.com.alura.adopet.api.service.email;

public interface EmailService {
    void enviarEmail(String to, String subject, String message);
}
