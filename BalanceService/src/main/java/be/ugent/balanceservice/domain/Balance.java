package be.ugent.balanceservice.domain;

import javax.persistence.*;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String visitorId;

    @Column(nullable = false)
    private double balance;

    private Balance() {
    }

    public Balance(String visitorId, double balance) {
        this.visitorId = visitorId;
        this.balance = balance;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addCreditToBalance(double amount){
        this.balance += amount;
        this.balance = (double) Math.round(this.balance * 100) / 100;
    }

    public void deductCreditFromBalance(double amount){
        this.balance -= amount;
        this.balance = (double) Math.round(this.balance * 100) / 100;
    }

}
