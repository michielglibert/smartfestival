package be.ugent.notificationservice.adapters.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import be.ugent.notificationservice.domain.LineupNotification;
import be.ugent.notificationservice.domain.NotificationService;

@Service
public class EventHandler {
	private final NotificationService notificationService;
	
	@Autowired
	public EventHandler(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@StreamListener(Channels.LINEUP_CHANGE)
	@SendTo(Channels.LINEUP_NOTIFICATION)
	public LineupNotification lineUpNotification(LineUpChange request) {
		return notificationService.createLineUpNotification(request);
	}
	
}