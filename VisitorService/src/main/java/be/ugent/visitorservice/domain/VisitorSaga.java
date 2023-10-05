package be.ugent.visitorservice.domain;

import be.ugent.visitorservice.adapters.messaging.MessageGateway;
import be.ugent.visitorservice.adapters.messaging.VerifyTicketRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorSaga {

    private static Logger logger = LoggerFactory.getLogger(VisitorSaga.class);

    private final MessageGateway gateway;

    @Autowired
    public VisitorSaga(MessageGateway gateway) {
        this.gateway = gateway;
    }

    public void  startVerifyTicketSaga(VerifyTicketRequestMessage verifyTicketRequestMessage) {
        logger.info("Started verify ticket request saga");
        gateway.sendVerifyTicketRequestMessage(verifyTicketRequestMessage);
    }
}
