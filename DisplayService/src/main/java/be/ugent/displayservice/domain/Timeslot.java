package be.ugent.displayservice.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Timeslot {
	@Id
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String artist;
	
	@Column(nullable=false)
	private String stage;
	
	@Column(nullable=false)
	private LocalDateTime startDateTime;
	
	@Column(nullable=false)
	private LocalDateTime endDateTime;
	
	private Timeslot () {}

	public Timeslot(Long id, String artist, String stage, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		this.id = id;
		this.artist = artist;
		this.stage = stage;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
