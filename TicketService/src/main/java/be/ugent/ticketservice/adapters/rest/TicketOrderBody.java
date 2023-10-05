package be.ugent.ticketservice.adapters.rest;

import java.util.List;


public class TicketOrderBody {
	
	private int numberOfTickets;
	private String email;
	private List<TicketInfo> ticketInfo;

	public TicketOrderBody(int numberOfTickets, String email, List<TicketInfo> ticketInfo) {
		this.numberOfTickets = numberOfTickets;
		this.email = email;
		this.ticketInfo = ticketInfo;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public List<TicketInfo> getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(List<TicketInfo> ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}