package be.ugent.productservice.adapters.messaging;

import be.ugent.productservice.domain.Order;
import be.ugent.productservice.domain.PayOrder;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.PAY_PRODUCT_ORDER)
    public void payProductOrder(PayOrder payOrder);
}
