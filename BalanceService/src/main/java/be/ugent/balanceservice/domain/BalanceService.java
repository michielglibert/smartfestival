package be.ugent.balanceservice.domain;

import be.ugent.balanceservice.adapters.messaging.MessageGateway;
import be.ugent.balanceservice.persistence.BalanceOrderRepository;
import be.ugent.balanceservice.persistence.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceOrderRepository balanceOrderRepository;
    private BalanceOrderSaga balanceOrderSaga;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository, BalanceOrderRepository balanceOrderRepository, BalanceOrderSaga balanceOrderSaga) {
        this.balanceRepository = balanceRepository;
        this.balanceOrderRepository = balanceOrderRepository;
        this.balanceOrderSaga = balanceOrderSaga;
    }

    public List<Balance> getAll(){
        return balanceRepository.findAll();
    }

    public double getBalance(String visitorId){
        double result = 0.0;
        Balance balance = balanceRepository.findByVisitorId(visitorId);
        if(balance != null){
            result = balance.getBalance();
        }
        return result;
    }

    public void addBalanceOrder(double amount, String visitorId) {
        BalanceOrder balanceOrder = new BalanceOrder(visitorId, amount);
        balanceOrderRepository.save(balanceOrder);
        balanceOrderSaga.payBalanceOrder(balanceOrder);
    }

    public void deductCreditFromBalance(double amount, String visitorId){
        Balance balance = balanceRepository.findByVisitorId(visitorId);
        if(balance != null){
            balance.deductCreditFromBalance(amount);
            balanceRepository.save(balance);
        }
    }

    public void addCreditToBalance(double amount, String visitorId){
        Balance balance = balanceRepository.findByVisitorId(visitorId);
        if(balance == null){
            balance = new Balance(visitorId, amount);
        } else {
            balance.addCreditToBalance(amount);
        }
        balanceRepository.save(balance);
    }
}
