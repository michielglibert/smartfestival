package be.ugent.visitorservice.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String VERIFY_TICKET = "verify_ticket";
    static final String TICKET_VERIFIED = "ticket_verified";
    static final String VISITOR_BANNED = "visitor_banned";

    @Input(TICKET_VERIFIED)
    SubscribableChannel ticketVerified();

    @Output(VERIFY_TICKET)
    MessageChannel verifyTicket();

    @Input(VISITOR_BANNED)
    SubscribableChannel banVisitor();

}
