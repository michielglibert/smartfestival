package be.ugent.balanceservice.domain;

import be.ugent.balanceservice.adapters.messaging.MessageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceOrderSaga {

    private final MessageGateway messageGateway;

    @Autowired
    public BalanceOrderSaga(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }


    public void payBalanceOrder(BalanceOrder balanceOrder) {
        messageGateway.payBalanceOrder(balanceOrder);
    }
}
