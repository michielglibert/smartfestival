package be.ugent.balanceservice.adapters.rest;

import be.ugent.balanceservice.adapters.messaging.MessageGateway;
import be.ugent.balanceservice.domain.BalanceOrder;
import be.ugent.balanceservice.domain.Balance;
import be.ugent.balanceservice.domain.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/balance")
@CrossOrigin(origins = "*")
public class BalanceRestController {

    private BalanceService balanceService;

    @Autowired
    public BalanceRestController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/all")
    public List<Balance> getAll(){
        return balanceService.getAll();
    }

    @GetMapping("/displayBalance/{visitorId}")
    public double getBalance(@PathVariable String visitorId){
        return balanceService.getBalance(visitorId);
    }

    @PostMapping("/addCreditToBalance/{amount}/{visitorId}")
    public void addCreditToBalance(@PathVariable double amount, @PathVariable String visitorId){
        balanceService.addBalanceOrder(amount, visitorId);
    }
}
