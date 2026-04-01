public class Address {

    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        if (street  == null || street.isBlank())  throw new IllegalArgumentException("Rua nao pode ser vazia.");
        if (city    == null || city.isBlank())    throw new IllegalArgumentException("Cidade nao pode ser vazia.");
        if (zipCode == null || zipCode.isBlank()) throw new IllegalArgumentException("CEP nao pode ser vazio.");
        this.street  = street;
        this.city    = city;
        this.zipCode = zipCode;
    }

    public String getStreet()  { return street; }
    public String getCity()    { return city; }
    public String getZipCode() { return zipCode; }

    @Override
    public String toString() {
        return street + ", " + city + " - " + zipCode;
    }
}
