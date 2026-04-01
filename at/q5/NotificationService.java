import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private final Map<String, NotificationChannel> channels = new HashMap<>();

    public NotificationService() {
        channels.put("EMAIL", new EmailNotification());
        channels.put("SMS",   new SmsNotification());
        channels.put("PUSH",  new PushNotification());
    }

    /**
     * Envia uma notificação pelo canal especificado.
     * A estrutura if/else foi substituída por polimorfismo via Map:
     * cada canal sabe como enviar sua própria mensagem.
     */
    public void notifyUser(String channel, String message) {
        NotificationChannel handler = channels.get(channel);
        if (handler == null) {
            throw new IllegalArgumentException("Canal desconhecido: " + channel);
        }
        handler.send(message);
    }

    /**
     * Registra um novo canal sem modificar nenhuma classe existente (OCP).
     * Ex.: registerChannel("WHATSAPP", new WhatsAppNotification());
     */
    public void registerChannel(String name, NotificationChannel channel) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nome do canal nao pode ser vazio.");
        if (channel == null)
            throw new IllegalArgumentException("Canal nao pode ser nulo.");
        channels.put(name, channel);
    }
}
