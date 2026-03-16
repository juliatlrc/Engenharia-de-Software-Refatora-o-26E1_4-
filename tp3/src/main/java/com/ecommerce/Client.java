package com.ecommerce;

/**
 * Representa o cliente de um pedido.
 * Encapsula nome e e-mail, evitando atributos públicos dispersos em Order.
 */
public class Client {

    private final String name;
    private final String email;

    public Client(String name, String email) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail do cliente não pode ser vazio.");
        this.name  = name;
        this.email = email;
    }

    public String getName()  { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
