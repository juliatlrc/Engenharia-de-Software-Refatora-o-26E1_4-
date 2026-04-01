public class PushNotification implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}
