package be.ugent.visitorservice.adapters.messaging;

import be.ugent.visitorservice.domain.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private VisitorService visitorService;

    @Autowired

    public EventHandler(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @StreamListener(Channels.TICKET_VERIFIED)
    public void ticketVerified(VerifyTicketResponseMessage verifyTicketResponseMessage) {
        logger.info("Received ticket verified response for ticket with id: " + verifyTicketResponseMessage.getTicketId());
        this.visitorService.performTicketVerifiedResponse(verifyTicketResponseMessage);
    }

    @StreamListener(Channels.VISITOR_BANNED)
    public void ticketVerified(BanVisitorRequestMessage banVisitorRequestMessage) {
        logger.info("Received request to ban visitor with id: " + banVisitorRequestMessage.getVisitorId());
        this.visitorService.bandVisitor(banVisitorRequestMessage);
    }
}
