import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private String name;
    private String email;
    private final List<Address> addresses = new ArrayList<>();

    public User(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public String getName()  { return name; }
    public String getEmail() { return email; }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nome do usuario nao pode ser vazio.");
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Email invalido.");
        this.email = email;
    }

    /**
     * Adiciona um endereço ao usuário de forma controlada.
     * Impede inserção de valores nulos e centraliza a regra de adição.
     */
    public void addAddress(Address address) {
        if (address == null)
            throw new IllegalArgumentException("Endereco nao pode ser nulo.");
        addresses.add(address);
    }

    /**
     * Retorna os endereços como lista imutável para evitar
     * modificações externas que contornem a validação do addAddress().
     */
    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public int getAddressCount() {
        return addresses.size();
    }
}
