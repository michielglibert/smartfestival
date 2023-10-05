package be.ugent.ticketservice.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.ticketservice.domain.TicketService;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
@RequestMapping("/ticket")
@CrossOrigin("*")
public class TicketRestController {

	private TicketService ticketService;

	@Autowired
	public TicketRestController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping("/create_ticket_order")
	public TicketReplyBody createTicketOrder(@RequestBody TicketOrderBody ticketOrderBody) {
		return this.ticketService.createTicketOrder(ticketOrderBody);
	}
	
	@PostMapping("/pay_ticket_order")
	public DeferredResult<TicketReplyBody> payTicketOrder(@RequestBody TicketPaymentBody ticketPaymentBody) {
		return this.ticketService.payTicketOrder(ticketPaymentBody);
	}

	@RequestMapping("/all")
	public AllTicketsResponseBody getAllTickets() {
		return this.ticketService.getAllTickets();
	}
}
