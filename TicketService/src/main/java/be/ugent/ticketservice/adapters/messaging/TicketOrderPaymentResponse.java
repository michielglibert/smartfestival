package be.ugent.ticketservice.adapters.messaging;

public class TicketOrderPaymentResponse {

    private String orderId;
    private boolean succes;

    public TicketOrderPaymentResponse() {

    }

    public TicketOrderPaymentResponse(String orderId, boolean succes) {
        this.orderId = orderId;
        this.succes = succes;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
