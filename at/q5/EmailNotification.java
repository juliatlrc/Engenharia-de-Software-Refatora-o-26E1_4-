public class EmailNotification implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}
