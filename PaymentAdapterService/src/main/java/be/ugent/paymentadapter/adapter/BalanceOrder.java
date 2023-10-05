package be.ugent.paymentadapter.adapter;

public class BalanceOrder {

    private Long id;
    private String visitorId;
    private double amount;

    public BalanceOrder(Long id, String visitorId, double amount) {
        this.id = id;
        this.visitorId = visitorId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
