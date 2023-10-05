package be.ugent.visitorservice.adapters.rest;

import be.ugent.visitorservice.domain.Visitor;

import java.util.List;

public class AllVisitorsResponse {

    private List<Visitor> visitors;

    public AllVisitorsResponse() {
    }

    public AllVisitorsResponse(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
