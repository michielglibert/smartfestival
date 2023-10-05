package be.ugent.displayservice.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.displayservice.persistence.MessageService;
import be.ugent.displayservice.domain.Message;

@RestController
@RequestMapping(path = "/display/messages")
@CrossOrigin(origins = "*")
public class MessageRestController {

	private MessageService messageService;
	
	@Autowired
	public MessageRestController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	/**
	 * Returns all messages.
	 */
	@GetMapping("/all")
	public Iterable<Message> getAll(){
		return messageService.getAll();
	}
	
	/**
	 * Returns the most important message. This is the message that has the highest priority and was created the earliest.
	 */
	@GetMapping("/top")
	public Message getMostImportantMessage() {
		return messageService.getMostImportantMessage();
	}
}
