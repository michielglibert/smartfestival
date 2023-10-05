package be.ugent.ticketservice.adapters.messaging;

public class TicketOrderPaymentRequest {

    private String id;
    
	
	public TicketOrderPaymentRequest() {
		super();
	}

    public TicketOrderPaymentRequest(String id) {
        this.id = id;
    }

    public String getId() {
    return id;
}
    public void setId(String id) {
        this.id = id;
    }
}
