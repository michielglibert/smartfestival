package be.ugent.lineupservice.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
	@Gateway(requestChannel = Channels.LINEUP_CHANGE)
	public void sendLineupChangeEvent(LineUpChange lineUpChange);
}
