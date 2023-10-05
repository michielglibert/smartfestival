package be.ugent.notificationservice.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import be.ugent.notificationservice.domain.SecurityNotification;

public interface SecurityNotificationRepository extends MongoRepository<SecurityNotification, String> {

}
