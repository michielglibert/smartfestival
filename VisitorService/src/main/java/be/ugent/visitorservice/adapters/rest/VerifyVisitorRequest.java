package be.ugent.visitorservice.adapters.rest;

public class VerifyVisitorRequest {

    private int visitorId;

    public VerifyVisitorRequest() {

    }

    public VerifyVisitorRequest(int visitorId) {
        this.visitorId = visitorId;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }
}
