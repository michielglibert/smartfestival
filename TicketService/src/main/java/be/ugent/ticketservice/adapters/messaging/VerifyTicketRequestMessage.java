package be.ugent.ticketservice.adapters.messaging;

public class VerifyTicketRequestMessage {

    private int ticketId;

    public VerifyTicketRequestMessage() {

    }

    public VerifyTicketRequestMessage(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
