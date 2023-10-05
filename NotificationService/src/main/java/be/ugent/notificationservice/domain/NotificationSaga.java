package be.ugent.notificationservice.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ugent.notificationservice.adapters.messaging.MessageGateway;

@Service
public class NotificationSaga {
	private static Logger logger = LoggerFactory.getLogger(NotificationSaga.class);
	
	private final MessageGateway gateway;
	
	@Autowired
	public NotificationSaga(MessageGateway gateway) {
		this.gateway = gateway;
	}
	
	public void startSendSecurityNotificationSaga(SecurityNotification securityNotification) {
		logger.info("Started saga");
		gateway.sendSecurityNotification(securityNotification);
	}
	
	public void replyToSecurityNotification(SecurityNotification securityNotification, String reply) {
		logger.info("Started saga");
		securityNotification.setReplied(true);
		securityNotification.setReply(reply);
		gateway.sendSecurityNotificationReply(securityNotification);
	}
}
