package be.ugent.displayservice.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.displayservice.adapters.messaging.DisplayMessage;
import be.ugent.displayservice.domain.Message;

@Service
public class MessageService {
	private final MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public Iterable<Message> getAll(){
		return messageRepository.findByDisplayedFalseOrderByPriorityDescCreatedDateTimeAsc();
	}
	
	public Message getMostImportantMessage() {
		Message message = messageRepository.findTopByDisplayedFalseOrderByPriorityDescCreatedDateTimeAsc();
		if(message != null) {
			message.setDisplayed(true);
			messageRepository.save(message);
		}
		return message;
	}
	
	public void addMessage(DisplayMessage message) {
		messageRepository.save(new Message(message.getTitle(), message.getMessage(), message.getPriority()));
	}
}
