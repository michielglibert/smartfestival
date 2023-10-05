package be.ugent.notificationservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import be.ugent.notificationservice.adapters.messaging.Priority;

@Document(collection = "security_notifications")
public class SecurityNotification extends Notification {
	private Priority priority;
	private Boolean replied;
	private String reply;
	
	public SecurityNotification() {
		super();
		this.replied = false;
	}
	
	public SecurityNotification(String title, String content, Priority priority) {
		super(title, content);
		this.priority = priority;
		this.replied = false;
	}
	
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Boolean getReplied() {
		return replied;
	}

	public void setReplied(Boolean replied) {
		this.replied = replied;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
