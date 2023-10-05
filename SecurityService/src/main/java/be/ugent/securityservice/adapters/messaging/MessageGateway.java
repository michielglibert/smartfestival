package be.ugent.securityservice.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.VISITOR_BANNED)
	public void banVisitor(VisitorIdMessage message);
}
