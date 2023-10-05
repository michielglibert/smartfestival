package be.ugent.productservice.domain;

import be.ugent.productservice.adapters.messaging.MessageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderSaga {

    private final MessageGateway messageGateway;

    @Autowired
    public ProductOrderSaga(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    public void payProductOrder(PayOrder payOrder){
        messageGateway.payProductOrder(payOrder);
    }
}
