package be.ugent.paymentadapter.adapter;

public class OrderRequest {

	private String id;
	
	public OrderRequest() {
		super();
	}

	public OrderRequest(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
