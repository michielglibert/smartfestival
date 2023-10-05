package be.ugent.ticketservice.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
    @Gateway(requestChannel = Channels.SEND_PAYMENT_ORDER)
    public void sendTicketOrderPaymentRequestMessage(TicketOrderPaymentRequest ticketOrderPaymentRequest);

    @Gateway(requestChannel = Channels.TICKET_VERIFIED)
    public void sendTicketVerificationResponseMessage(VerifyTicketResponseMessage verifyTicketResponseMessage);
}
