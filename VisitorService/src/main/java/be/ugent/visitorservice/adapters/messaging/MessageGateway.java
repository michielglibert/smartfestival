package be.ugent.visitorservice.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
    @Gateway(requestChannel = Channels.VERIFY_TICKET)
    public void sendVerifyTicketRequestMessage(VerifyTicketRequestMessage verifyTicketRequestMessage);
}
