package be.ugent.displayservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String LINEUP_CHANGE = "lineup_change";
	static final String DISPLAY_MESSAGE = "display_message";
	
	@Input(LINEUP_CHANGE)
	SubscribableChannel lineupChange();
	
	@Input(DISPLAY_MESSAGE)
	SubscribableChannel displayMessage();
}
