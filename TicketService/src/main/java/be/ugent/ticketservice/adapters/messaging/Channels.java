package be.ugent.ticketservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String TICKET_ORDER_PAID = "ticket_order_payed";
	static final String TICKET_VERIFIED = "ticket_verified";
	static final String SEND_PAYMENT_ORDER = "pay_ticket_order";
	static final String VERIFY_TICKET = "verify_ticket";

	@Input(TICKET_ORDER_PAID)
	SubscribableChannel ticketOrderPaid();

	@Input(VERIFY_TICKET)
	SubscribableChannel verifyTicket();

	@Output(SEND_PAYMENT_ORDER)
	MessageChannel payTicketOrder();

	@Output(TICKET_VERIFIED)
	MessageChannel ticketVerified();
}
