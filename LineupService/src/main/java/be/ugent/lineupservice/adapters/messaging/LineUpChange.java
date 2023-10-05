package be.ugent.lineupservice.adapters.messaging;

import java.time.LocalDateTime;

import be.ugent.lineupservice.domain.Timeslot;

public class LineUpChange {
	private Long timeslotId;
	private String artist;
	private String stage;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private EventType type;
	
	public LineUpChange(Timeslot timeslot, EventType type) {
		this.timeslotId = timeslot.getId();
		this.artist = timeslot.getArtist();
		this.stage = timeslot.getStage();
		this.startDateTime = timeslot.getStartDateTime();
		this.endDateTime = timeslot.getEndDateTime();
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
