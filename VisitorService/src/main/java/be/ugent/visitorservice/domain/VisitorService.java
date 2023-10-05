package be.ugent.visitorservice.domain;

import be.ugent.visitorservice.adapters.messaging.BanVisitorRequestMessage;
import be.ugent.visitorservice.adapters.messaging.VerifyTicketRequestMessage;
import be.ugent.visitorservice.adapters.messaging.VerifyTicketResponseMessage;
import be.ugent.visitorservice.adapters.rest.AllVisitorsResponse;
import be.ugent.visitorservice.adapters.rest.VerifyResponse;
import be.ugent.visitorservice.adapters.rest.VerifyTicketRequest;
import be.ugent.visitorservice.adapters.rest.VerifyVisitorRequest;
import be.ugent.visitorservice.persistence.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class VisitorService {

    private final VisitorSaga visitorSaga;
    private final VisitorRepository visitorRepository;
    private final Map<Integer, DeferredResult<VerifyResponse>> deferredResults;

    @Autowired
    public VisitorService(VisitorSaga visitorSaga, VisitorRepository visitorRepository) {
        this.visitorSaga = visitorSaga;
        this.visitorRepository = visitorRepository;
        this.deferredResults = new HashMap<>(10);
    }

    public void sendVerifyTicketRequestMessage(VerifyTicketRequestMessage verifyTicketRequestMessage) {
        visitorSaga.startVerifyTicketSaga(verifyTicketRequestMessage);
    }

    public DeferredResult<VerifyResponse> verifyTicket(VerifyTicketRequest request) {

        DeferredResult<VerifyResponse> deferredResult = new DeferredResult<>(10000l);

        deferredResult.onTimeout(() -> deferredResult.setErrorResult("Request timeout occurred."));

        this.deferredResults.put(request.getTicketId(), deferredResult);

        try {
            sendVerifyTicketRequestMessage(new VerifyTicketRequestMessage(request.getTicketId()));
        }
        catch(Exception e) {
            deferredResult.setErrorResult("Failed to verify ticker order");
            this.deferredResults.remove(request.getTicketId());
        }

        return deferredResult;
    }

    public void performTicketVerifiedResponse(VerifyTicketResponseMessage verifyTicketResponseMessage) {

        DeferredResult<VerifyResponse> deferredResult = this.deferredResults.remove(verifyTicketResponseMessage.getTicketId());

        if(verifyTicketResponseMessage.isVerified()) {
            Visitor visitor = this.visitorRepository.save(new Visitor(verifyTicketResponseMessage.getFirstName(), verifyTicketResponseMessage.getLastName(), verifyTicketResponseMessage.getDateOfBirth(), false));
            deferredResult.setResult(new VerifyResponse(true, visitor));
        }
        else {
            deferredResult.setResult(new VerifyResponse(false, null));
        }
    }

    public VerifyResponse verifyVisitor(VerifyVisitorRequest request) {

        Visitor visitor = this.visitorRepository.getVisitorById(request.getVisitorId());

        if(visitor != null && !visitor.isBanned()) {
            return new VerifyResponse(true, visitor);
        }
        else {
            return new VerifyResponse(false, null);
        }
    }

    public void bandVisitor(BanVisitorRequestMessage banVisitorRequestMessage) {
        this.visitorRepository.banVisitor(banVisitorRequestMessage.getVisitorId());
    }

    public AllVisitorsResponse getAllVisitors() {
        return new AllVisitorsResponse(this.visitorRepository.getAllVisitors());
    }
}
