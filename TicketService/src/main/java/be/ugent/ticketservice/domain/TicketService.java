package be.ugent.ticketservice.domain;

import be.ugent.ticketservice.adapters.messaging.*;
import be.ugent.ticketservice.adapters.rest.AllTicketsResponseBody;
import be.ugent.ticketservice.adapters.rest.TicketInfo;
import be.ugent.ticketservice.adapters.rest.TicketOrderBody;
import be.ugent.ticketservice.adapters.rest.TicketPaymentBody;
import be.ugent.ticketservice.adapters.rest.TicketReplyBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.ticketservice.persistence.TicketRepository;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

@Service
public class TicketService {

	private final TicketSaga ticketSaga;
	private final TicketRepository ticketRepository;
	private final Map<String, DeferredResult<TicketReplyBody>> deferredResults;
	
	@Autowired
	public TicketService(TicketSaga ticketSaga, TicketRepository ticketRepository) {
		this.ticketSaga = ticketSaga;
		this.ticketRepository = ticketRepository;
		this.deferredResults = new HashMap<>(10);
	}

	public void sendTicketOrderPaymentSaga(TicketOrderPaymentRequest ticketOrderPaymentRequest) {
		ticketSaga.startSendTicketOrderPaymentSaga(ticketOrderPaymentRequest);
	}

	public void replyToTicketVerification(VerifyTicketRequestMessage verifyTicketRequestMessage) {

		Ticket ticket = ticketRepository.getTicketById(verifyTicketRequestMessage.getTicketId());

		if(ticket != null && !ticket.isActivated()) {
			this.ticketRepository.activateTicket(ticket.getId());
			this.ticketSaga.replyToTicketVerification(new VerifyTicketResponseMessage(ticket.getId().intValue(), true, ticket.getFirstName(), ticket.getLastName(), ticket.getDateOfBirth()));
		}
		else {
			this.ticketSaga.replyToTicketVerification(new VerifyTicketResponseMessage(verifyTicketRequestMessage.getTicketId(), false, null, null, null));
		}
	}

	public DeferredResult<TicketReplyBody> payTicketOrder(TicketPaymentBody ticketPaymentBody) {
		DeferredResult<TicketReplyBody> deferredResult = new DeferredResult<TicketReplyBody>(10000l);

		// Order advances to payment
		if(!ticketPaymentBody.isCanceled()) {

			deferredResult.onTimeout(() -> deferredResult.setErrorResult("Request timeout occurred."));

			this.deferredResults.put(ticketPaymentBody.getOrderId(), deferredResult);

			try {
				sendTicketOrderPaymentSaga(new TicketOrderPaymentRequest(ticketPaymentBody.getOrderId()));
			} catch (Exception e) {
				deferredResult.setErrorResult("Failed to pay ticket order.");
				this.deferredResults.remove(ticketPaymentBody.getOrderId());
			}
		}
		// Order was canceled
		else {
			ticketRepository.cancelOrder(ticketPaymentBody.getOrderId());
			this.deferredResults.remove(ticketPaymentBody.getOrderId());
			deferredResult.setResult(new TicketReplyBody(true, "Your ticket order was canceled!", new ArrayList<>(), 0.0, ticketPaymentBody.getOrderId()));
		}

		return deferredResult;
	}

	public void performTicketPayResponse(TicketOrderPaymentResponse ticketOrderPaymentResponse) {

		DeferredResult<TicketReplyBody> deferredResult = this.deferredResults.remove(ticketOrderPaymentResponse.getOrderId());

		List<Ticket> tickets = ticketRepository.getTicketsForOrderId(ticketOrderPaymentResponse.getOrderId());
		ticketRepository.completeOrder(ticketOrderPaymentResponse.getOrderId());
		double price = 0.0;

		for(Ticket ticket : tickets) {
			price += ticket.getPrice();
			ticket.setStatus(TicketStatus.SOLD);
		}

		TicketReplyBody ticketReplyBody = new TicketReplyBody(true, "Payment was completed!", tickets, price, ticketOrderPaymentResponse.getOrderId());

		deferredResult.setResult(ticketReplyBody);
	}

	public TicketReplyBody createTicketOrder(TicketOrderBody ticketOrderBody) {
		Long numberOfAvailableTickets = ticketRepository.countByStatus(TicketStatus.AVAILABLE);
		double price = 0.0;

		if(ticketOrderBody.getNumberOfTickets() <= numberOfAvailableTickets) {
			List<Ticket> availableTickets = ticketRepository.getAvailableTickets(ticketOrderBody.getNumberOfTickets());
			String orderId = UUID.randomUUID().toString();

			for(int i = 0; i < ticketOrderBody.getNumberOfTickets(); i++) {
				TicketInfo ticketInfo = ticketOrderBody.getTicketInfo().get(i);
				Ticket ticket = availableTickets.get(i);

				ticket.setStatus(TicketStatus.RESERVED);
				ticket.setFirstName(ticketInfo.getFirstName());
				ticket.setLastName(ticketInfo.getLastName());
				ticket.setDateOfBirth(ticketInfo.getDateOfBirth());
				ticket.setEmail(ticketOrderBody.getEmail());
				ticket.setOrderId(orderId);

				price += ticket.getPrice();

				this.ticketRepository.updateTicket(availableTickets.get(i).getId(), ticket.getStatus().toString(), ticket.getFirstName(), ticket.getLastName(), ticket.getEmail(), ticket.getDateOfBirth(), ticket.getOrderId());
			}

			return new TicketReplyBody(true, "Your tickets were successfully reserved!", availableTickets, price, orderId);
		}

		else {
			return new TicketReplyBody(false, "There are not enough available tickets left!", new ArrayList<>(), 0.0, null);
		}
	}

    public AllTicketsResponseBody getAllTickets() {
		return new AllTicketsResponseBody(this.ticketRepository.getAllSoldAndReservedTickets());
    }
}
