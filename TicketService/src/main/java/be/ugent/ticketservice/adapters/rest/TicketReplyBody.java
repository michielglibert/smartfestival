package be.ugent.ticketservice.adapters.rest;

import be.ugent.ticketservice.domain.Ticket;

import java.util.List;

public class TicketReplyBody {

    private boolean success;
    private String message;
    private double price;
    private String orderId;
    private List<Ticket> tickets;

    public TicketReplyBody(boolean success, String message, List<Ticket> tickets, double price, String orderId) {
        this.success = success;
        this.message = message;
        this.tickets = tickets;
        this.price = price;
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
