package be.ugent.visitorservice.adapters.rest;

import be.ugent.visitorservice.domain.Visitor;

public class VerifyResponse {

    private boolean verified;
    private Visitor visitor;

    public VerifyResponse(boolean verified, Visitor visitor) {
        this.verified = verified;
        this.visitor = visitor;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
