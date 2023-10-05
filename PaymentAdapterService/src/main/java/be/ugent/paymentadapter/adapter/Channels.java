package be.ugent.paymentadapter.adapter;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String PAY_TICKER_ORDER = "pay_ticket_order";
	static final String TICKER_ORDER_PAYED = "ticket_order_payed";
	static final String PAY_BALANCE_ORDER = "pay_balance_order";
	static final String BALANCE_ORDER_COMPLETED = "balance_order_COMPLETED";
	
	@Input(PAY_TICKER_ORDER)
	SubscribableChannel payTicketOrder();
	
	@Output(TICKER_ORDER_PAYED)
	MessageChannel ticketOrderPayed();
	
	@Input(PAY_BALANCE_ORDER)
	SubscribableChannel payBalanceOrder();
	
	@Output(BALANCE_ORDER_COMPLETED)
	MessageChannel balanceOrderCompleted();
}
