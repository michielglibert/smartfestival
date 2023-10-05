package be.ugent.balanceservice.adapters.messaging;

import be.ugent.balanceservice.domain.BalanceOrder;
import be.ugent.balanceservice.domain.BalanceService;
import be.ugent.balanceservice.persistence.BalanceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    private final BalanceService balanceService;
    private final BalanceOrderRepository balanceOrderRepository;

    @Autowired
    public EventHandler(BalanceService balanceService, BalanceOrderRepository balanceOrderRepository) {
        this.balanceService = balanceService;
        this.balanceOrderRepository = balanceOrderRepository;
    }

    @StreamListener(Channels.PAY_PRODUCT_ORDER)
    @SendTo(Channels.PRODUCT_ORDER_COMPLETED)
    public ProductOrderResponse payProductOrder(PayOrder payOrder){
        double availableBalance = this.balanceService.getBalance(payOrder.getVisitorId());
        ProductOrderResponse por = new ProductOrderResponse(payOrder.getOrderId(), false);
        if(payOrder.getTotal() <= availableBalance) {
            balanceService.deductCreditFromBalance(payOrder.getTotal(), payOrder.getVisitorId());
            por.setSuccess(true);
        }
        return por;
    }

    @StreamListener(Channels.BALANCE_ORDER_COMPLETED)
    public void balanceOrderCompleted(OrderPaidResponse orderPaidResponse){
        boolean success = orderPaidResponse.isSuccess();
        if(success){
            BalanceOrder bo = this.balanceOrderRepository.findById(Long.parseLong(orderPaidResponse.getOrderId())).get();
            this.balanceService.addCreditToBalance(bo.getAmount(), bo.getVisitorId());
        }
    }

}
