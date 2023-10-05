package be.ugent.visitorservice.adapters.messaging;

public class BanVisitorRequestMessage {

    private int visitorId;

    public BanVisitorRequestMessage() {
    }

    public BanVisitorRequestMessage(int visitorId) {
        this.visitorId = visitorId;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }
}
