package be.ugent.notificationservice.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lineup_notifications")
public class LineupNotification extends Notification {
	private Long timeslotId;
	private String artist;
	private String stage;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private EventType type;

	public LineupNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public LineupNotification(String title, String content, long timeslotId, String artist, String stage, LocalDateTime startDateTime, LocalDateTime endDateTime, EventType type) {
		super(title,content);
		this.timeslotId = timeslotId;
		this.artist = artist;
		this.stage = stage;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.type = type;
	}

	public Long getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(Long timeslotId) {
		this.timeslotId = timeslotId;
	}


	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	
}