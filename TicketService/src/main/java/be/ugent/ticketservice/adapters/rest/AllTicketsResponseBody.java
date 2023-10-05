package be.ugent.ticketservice.adapters.rest;

import be.ugent.ticketservice.domain.Ticket;

import java.util.List;

public class AllTicketsResponseBody {

    private List<Ticket> tickets;

    public AllTicketsResponseBody() {

    }

    public AllTicketsResponseBody(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
