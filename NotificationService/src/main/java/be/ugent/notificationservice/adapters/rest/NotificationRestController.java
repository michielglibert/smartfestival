package be.ugent.notificationservice.adapters.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.notificationservice.adapters.messaging.SecurityReply;
import be.ugent.notificationservice.domain.DisplayMessage;
import be.ugent.notificationservice.domain.NotificationService;
import be.ugent.notificationservice.domain.SecurityNotification;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*")
public class NotificationRestController {
	private final NotificationService notificationService;

	@Autowired
	public NotificationRestController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@GetMapping("/all")
	public List<SecurityNotification> getSecurityNotifications() {
		return notificationService.getSecurityNotifications();
	}
	
	@PostMapping("/send_security_notification")
	public void sendSecurityNotification(@RequestBody SecurityNotification securityNotification) {
		notificationService.sendSecurityNotification(securityNotification);
	}
	
	@PostMapping("/reply_to_security_notfication/{id}")
	public void replyToSecurityNotification(@PathVariable("id") String id, @RequestBody SecurityReply reply) {
		notificationService.replyToSecurityNotification(id, reply.getReply());
	}
	
	@PostMapping("/send_display_message")
	public void sendDisplayMessage(@RequestBody DisplayMessage message) {
		notificationService.sendDisplayMessage(message);
	}
}
