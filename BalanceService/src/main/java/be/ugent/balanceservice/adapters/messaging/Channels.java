package be.ugent.balanceservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String PAY_PRODUCT_ORDER = "pay_product_order";
    static final String PAY_BALANCE_ORDER = "pay_balance_order";
    static final String BALANCE_ORDER_COMPLETED = "balance_order_completed";
    static final String PRODUCT_ORDER_COMPLETED = "product_order_completed";

    @Input(PAY_PRODUCT_ORDER)
    SubscribableChannel payProductOrder();

    @Output(PAY_BALANCE_ORDER)
    MessageChannel payBalanceOrder();

    @Input(BALANCE_ORDER_COMPLETED)
    SubscribableChannel balanceOrderCompleted();

    @Output(PRODUCT_ORDER_COMPLETED)
    MessageChannel productOrderCompleted();
}
