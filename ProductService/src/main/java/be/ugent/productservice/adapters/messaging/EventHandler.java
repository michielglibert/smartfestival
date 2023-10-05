package be.ugent.productservice.adapters.messaging;

import be.ugent.productservice.domain.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    private final ProductOrderService productOrderService;

    @Autowired
    public EventHandler(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @StreamListener(Channels.PRODUCT_ORDER_COMPLETED)
    public void productOrderCompleted(ProductOrderResponse productOrderResponse){
        if (productOrderResponse.isSuccess())
            this.productOrderService.markOrderAsPaid(productOrderResponse.getOrderId());
    }
}
