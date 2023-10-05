package be.ugent.lineupservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	static final String LINEUP_CHANGE = "lineup_change";
	
	@Output(LINEUP_CHANGE)
	MessageChannel sendLineUpChange();
}
