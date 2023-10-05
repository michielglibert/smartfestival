package be.ugent.notificationservice.domain;

public class DisplayMessage {
	private String title;
	
	private String message;
	
	private int priority;

	public DisplayMessage(String title, String message, int priority) {
		this.title = title;
		this.message = message;
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
