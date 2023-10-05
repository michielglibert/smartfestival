package be.ugent.ticketservice.domain;

public enum TicketStatus {
	AVAILABLE("AVAILABLE"),
	RESERVED("RESERVED"),
	SOLD("SOLD");
	
	public final String status;
	 
    private TicketStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
