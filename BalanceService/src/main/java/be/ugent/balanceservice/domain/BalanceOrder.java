package be.ugent.balanceservice.domain;

import javax.persistence.*;

@Entity
public class BalanceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String visitorId;

    @Column(nullable = false)
    private double amount;

    public BalanceOrder() {
    }

    public BalanceOrder(String visitorId, double amount) {
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