package be.ugent.notificationservice.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.notificationservice.domain.DisplayMessage;
import be.ugent.notificationservice.domain.SecurityNotification;

@MessagingGateway
public interface MessageGateway {
	@Gateway(requestChannel = Channels.SECURITY_NOTIFICATION)
	public void sendSecurityNotification(SecurityNotification securityNotification);
	@Gateway(requestChannel = Channels.SECURITY_NOTIFICATION_REPLY)
	public void sendSecurityNotificationReply(SecurityNotification securityNotification);
	@Gateway(requestChannel = Channels.DISPLAY_MESSAGE)
	public void sendDisplayMessage(DisplayMessage message);
}
