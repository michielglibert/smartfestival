package be.ugent.securityservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "incidents")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Incident {
	@Id
	private String id;
	private String name;
	private String description;
	private LocalDateTime time;
	private Location location;
	private Severity severity;
	private List<String> users;
	
	public Incident() {
			
	}
	
	public Incident(String id, String name, String description, LocalDateTime time, Location location,
			Severity severity, List<String> users) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
		this.location = location;
		this.severity = severity;
		this.users = users;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	
}
