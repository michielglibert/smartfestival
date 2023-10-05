package be.ugent.visitorservice.adapters.rest;


import be.ugent.visitorservice.domain.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
@RequestMapping("/visitor")
@CrossOrigin("*")
public class VisitorRestController {

    private VisitorService visitorService;

    @Autowired
    public VisitorRestController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping("/verify_ticket")
    public DeferredResult<VerifyResponse> verifyTicket(@RequestBody VerifyTicketRequest request) {
        return this.visitorService.verifyTicket(request);
    }

    @PostMapping("/verify_visitor")
    public VerifyResponse verifyVisitor(@RequestBody VerifyVisitorRequest request) {
        return this.visitorService.verifyVisitor(request);
    }

    @GetMapping("/all")
    public AllVisitorsResponse getAllVisitors() {
        return this.visitorService.getAllVisitors();
    }
}
