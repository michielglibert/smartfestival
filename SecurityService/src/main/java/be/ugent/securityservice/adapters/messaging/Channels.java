package be.ugent.securityservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	static final String VISITOR_BANNED = "visitor_banned";
	
	@Output(VISITOR_BANNED)
	MessageChannel visitorBanned();
}
