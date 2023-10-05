package be.ugent.ticketservice.adapters.messaging;

import be.ugent.ticketservice.domain.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;


@Service
public class EventHandler {

    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private final TicketService ticketService;

    @Autowired
    public EventHandler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @StreamListener(Channels.TICKET_ORDER_PAID)
    public void ticketOrderPaid(TicketOrderPaymentResponse ticketOrderPaymentResponse) {
        logger.info("Recieved ticket paid response with id: " + ticketOrderPaymentResponse.getOrderId());
        this.ticketService.performTicketPayResponse(ticketOrderPaymentResponse);
    }

    @StreamListener(Channels.VERIFY_TICKET)
    public void verifyTicket(VerifyTicketRequestMessage verifyTicketRequestMessage) {
        logger.info("Received verify ticket request for ticket with id: " + verifyTicketRequestMessage.getTicketId());
        ticketService.replyToTicketVerification(verifyTicketRequestMessage);
    }
}
