package be.ugent.productservice.domain;

public class PayOrder {

    private String visitorId;
    private String orderId;
    private double total;

    public PayOrder(String visitorId, String orderId, double total) {
        this.visitorId = visitorId;
        this.orderId = orderId;
        this.total = total;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
