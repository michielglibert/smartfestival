package be.ugent.notificationservice.adapters.messaging;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String LINEUP_CHANGE= "lineup_change";
	static final String LINEUP_NOTIFICATION = "lineup_notification";
	static final String SECURITY_NOTIFICATION = "security_notification";
	static final String SECURITY_NOTIFICATION_REPLY = "security_notification_reply";
	static final String DISPLAY_MESSAGE = "display_message";
	
	@Input(LINEUP_CHANGE)
	SubscribableChannel lineupChange();
	
	@Output(LINEUP_NOTIFICATION)
	MessageChannel sendLineupNotification();

	@Output(SECURITY_NOTIFICATION)
	MessageChannel sendSecurityNotification();		
	
	@Output(SECURITY_NOTIFICATION_REPLY)
	MessageChannel sendNotification();
	
	@Output(DISPLAY_MESSAGE)
	MessageChannel sendDisplayMessage();
}
