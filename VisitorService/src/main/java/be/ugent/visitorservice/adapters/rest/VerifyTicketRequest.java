package be.ugent.visitorservice.adapters.rest;

public class VerifyTicketRequest {

    private int ticketId;

    public VerifyTicketRequest() {
        
    }

    public VerifyTicketRequest(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
