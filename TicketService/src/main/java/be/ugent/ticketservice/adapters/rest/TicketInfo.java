package be.ugent.ticketservice.adapters.rest;

import java.time.LocalDate;

public class TicketInfo {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;

	public TicketInfo(String firstName, String lasteName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lasteName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
