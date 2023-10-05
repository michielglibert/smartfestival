package be.ugent.displayservice.adapters.messaging;

import java.time.LocalDateTime;

public class LineUpChange {
	private Long timeslotId;
	private String artist;
	private String stage;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private EventType type;
	
	public LineUpChange(Long timeslotId, String artist, String stage, LocalDateTime startDateTime, LocalDateTime endDateTime, EventType type) {
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
