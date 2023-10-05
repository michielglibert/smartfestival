package be.ugent.ticketservice.adapters.rest;

public class TicketPaymentBody {

    private String orderId;
    private boolean canceled;

    public TicketPaymentBody(boolean canceled, String orderId) {
        this.canceled = canceled;
        this.orderId = orderId;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
