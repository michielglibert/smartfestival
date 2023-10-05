package be.ugent.productservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    static final String PAY_PRODUCT_ORDER = "pay_product_order";
    static final String PRODUCT_ORDER_COMPLETED = "product_order_completed";

    @Output(PAY_PRODUCT_ORDER)
    MessageChannel payProductOrder();

    @Input(PRODUCT_ORDER_COMPLETED)
    SubscribableChannel productOrderCompleted();
}
