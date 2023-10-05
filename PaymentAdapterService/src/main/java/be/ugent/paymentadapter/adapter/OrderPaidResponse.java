package be.ugent.paymentadapter.adapter;

public class OrderPaidResponse {

	private String orderId;
	private boolean success;

	public OrderPaidResponse(String id, boolean success) {
		this.orderId = id;
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
