package be.ugent.notificationservice.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.notificationservice.adapters.messaging.LineUpChange;
import be.ugent.notificationservice.adapters.messaging.MessageGateway;
import be.ugent.notificationservice.persistence.LineupNotificationRepository;
import be.ugent.notificationservice.persistence.SecurityNotificationRepository;

@Service
public class NotificationService {
	private final MessageGateway messageGateway;
	private final NotificationSaga notificationSaga;
	private final SecurityNotificationRepository securityNotificationRepository;
	private final LineupNotificationRepository lineupNotificationRepository;
	
	@Autowired
	public NotificationService(MessageGateway messageGateway, NotificationSaga notificationSaga, SecurityNotificationRepository securityNotificationRepository, LineupNotificationRepository lineupNotificationRepository) {
		this.messageGateway = messageGateway;
		this.notificationSaga = notificationSaga;
		this.securityNotificationRepository = securityNotificationRepository;
		this.lineupNotificationRepository = lineupNotificationRepository;
	}
	
	public void sendSecurityNotification(SecurityNotification securityNotification) {
		securityNotificationRepository.save(securityNotification);
		notificationSaga.startSendSecurityNotificationSaga(securityNotification);
	}

	public void replyToSecurityNotification(String id, String reply) {
		SecurityNotification securityNotification = securityNotificationRepository.findById(id).get();		
		notificationSaga.replyToSecurityNotification(securityNotification, reply);
		securityNotificationRepository.save(securityNotification);
	}
	
	public List<SecurityNotification> getSecurityNotifications() {
		return securityNotificationRepository.findAll();
	}
	
	public void sendDisplayMessage(DisplayMessage message) {
		messageGateway.sendDisplayMessage(message);
	}
	
	public LineupNotification createLineUpNotification(LineUpChange change) {
		LineupNotification lineupNotification;
		if(change.getType() == EventType.CREATED) {
			lineupNotification = new LineupNotification("Artist added!","The line up for " + change.getArtist() + " has been added for stage " + change.getStage() + ".", change.getTimeslotId(), change.getArtist(), change.getStage(), change.getStartDateTime(), change.getEndDateTime(),change.getType());
		} else {
			lineupNotification = new LineupNotification("Artist removed!","The line up for " + change.getArtist() + " is no longer valid.", change.getTimeslotId(), change.getArtist(), change.getStage(), change.getStartDateTime(), change.getEndDateTime(),change.getType());
		}
		lineupNotificationRepository.save(lineupNotification);
		return lineupNotification;
	}
}
