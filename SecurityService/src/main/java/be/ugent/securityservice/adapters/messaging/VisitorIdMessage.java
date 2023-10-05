package be.ugent.securityservice.adapters.messaging;

public class VisitorIdMessage {

	private int visitorId;

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	
	public VisitorIdMessage() {
	}
	
	public VisitorIdMessage(int visitorId) {
		this.visitorId = visitorId;
	}
}
