package be.ugent.productservice.adapters.messaging;

public class ProductOrderResponse {

    private String orderId;
    private boolean success;

    public ProductOrderResponse(String orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
