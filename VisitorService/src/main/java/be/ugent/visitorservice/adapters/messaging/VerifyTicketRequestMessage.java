package be.ugent.visitorservice.adapters.messaging;

public class VerifyTicketRequestMessage {

    private int ticketId;

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
