package be.ugent.ticketservice.domain;

import be.ugent.ticketservice.adapters.messaging.TicketOrderPaymentRequest;
import be.ugent.ticketservice.adapters.messaging.VerifyTicketResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.ticketservice.adapters.messaging.MessageGateway;

@Service
public class TicketSaga {

	private static Logger logger = LoggerFactory.getLogger(TicketSaga.class);
	
	private final MessageGateway gateway;
	
	@Autowired
	public TicketSaga(MessageGateway gateway) {
		this.gateway = gateway;
	}

	public void startSendTicketOrderPaymentSaga(TicketOrderPaymentRequest ticketOrderPaymentRequest) {
		logger.info("Started ticket order payment saga");
		gateway.sendTicketOrderPaymentRequestMessage(ticketOrderPaymentRequest);
	}

	public void replyToTicketVerification(VerifyTicketResponseMessage verifyTicketResponseMessage) {
		logger.info("Started verify ticket response saga");
		gateway.sendTicketVerificationResponseMessage(verifyTicketResponseMessage);
	}
}
