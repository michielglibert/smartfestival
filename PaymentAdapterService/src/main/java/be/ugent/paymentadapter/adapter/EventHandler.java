package be.ugent.paymentadapter.adapter;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {
	@StreamListener(Channels.PAY_TICKER_ORDER)
	@SendTo(Channels.TICKER_ORDER_PAYED)
	public OrderPaidResponse payTicketOrder(OrderRequest orderRequest) {
		return payOrder(orderRequest);
	}
	
	@StreamListener(Channels.PAY_BALANCE_ORDER)
	@SendTo(Channels.BALANCE_ORDER_COMPLETED)
	public OrderPaidResponse payBalanceOrder(BalanceOrder balanceOrder) {
		OrderRequest or = new OrderRequest(balanceOrder.getId().toString());
		return payOrder(or);
	}
	
	private OrderPaidResponse payOrder(OrderRequest orderRequest) {
		//We gaan ervan uit dat de betaling goed verloopt
		//Normaal gebeurt hier de communicatie met een betaling API
		OrderPaidResponse orderPayedResponse = new OrderPaidResponse(orderRequest.getId(), true);
		return orderPayedResponse;
	}
}