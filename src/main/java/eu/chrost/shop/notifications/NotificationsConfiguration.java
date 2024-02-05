package eu.chrost.shop.notifications;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationsConfiguration {
    @Bean
    public NotificationEventListener notificationEventListener() {
        return new NotificationEventListener();
    }
}
