package be.ugent.displayservice.adapters.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.displayservice.persistence.MessageService;
import be.ugent.displayservice.persistence.TimeslotService;

@Service
public class EventHandler {
	private final TimeslotService timeslotService;
	private final MessageService messageService;
	
	@Autowired
	public EventHandler(TimeslotService timeslotService, MessageService messageService) {
		this.timeslotService = timeslotService;
		this.messageService = messageService;
	}
	
	@StreamListener(Channels.LINEUP_CHANGE)
	public void lineUpNotification(LineUpChange change) {
		this.timeslotService.editTimetable(change);
	}
	
	@StreamListener(Channels.DISPLAY_MESSAGE)
	public void displayMessageNotification(DisplayMessage message) {
		this.messageService.addMessage(message);
	}
}
