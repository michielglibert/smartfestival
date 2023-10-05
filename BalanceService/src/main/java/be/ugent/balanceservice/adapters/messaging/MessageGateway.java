package be.ugent.balanceservice.adapters.messaging;

import be.ugent.balanceservice.domain.BalanceOrder;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.PAY_BALANCE_ORDER)
    public void payBalanceOrder(BalanceOrder balanceOrder);
}
