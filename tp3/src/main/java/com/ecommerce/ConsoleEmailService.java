package com.ecommerce;

/**
 * Implementação de EmailService que imprime o e-mail no console.
 * Em produção, seria substituída por uma implementação real (SMTP, SendGrid, etc.).
 */
public class ConsoleEmailService implements EmailService {

    @Override
    public void send(String to, String message) {
        System.out.println("──────────────────────────────────────");
        System.out.println("📧 E-mail enviado");
        System.out.println("Para    : " + to);
        System.out.println("Mensagem: " + message);
        System.out.println("──────────────────────────────────────");
    }
}
