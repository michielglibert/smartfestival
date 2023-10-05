package be.ugent.notificationservice.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import be.ugent.notificationservice.domain.LineupNotification;

public interface LineupNotificationRepository extends MongoRepository<LineupNotification,String>{

}