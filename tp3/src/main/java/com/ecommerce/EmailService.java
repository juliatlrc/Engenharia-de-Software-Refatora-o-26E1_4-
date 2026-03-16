package com.ecommerce;

/**
 * Contrato para envio de e-mails.
 * Permite substituir a implementação real por um mock em testes.
 */
public interface EmailService {
    void send(String to, String message);
}
