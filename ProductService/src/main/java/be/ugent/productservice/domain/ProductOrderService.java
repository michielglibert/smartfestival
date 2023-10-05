package be.ugent.productservice.domain;

import be.ugent.productservice.adapters.messaging.MessageGateway;
import be.ugent.productservice.persistence.OrderRepository;
import be.ugent.productservice.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {
    private static Logger logger = LoggerFactory.getLogger(ProductOrderService.class);
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductOrderSaga productOrderSaga;

    @Autowired
    public ProductOrderService(ProductRepository productRepository, OrderRepository orderRepository, ProductOrderSaga productOrderSaga) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productOrderSaga = productOrderSaga;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void createProductOrder(Order order){
        // automatic ID-generation
        Order o = new Order(order.getVisitorId(), order.getProducts(), order.getFestivalStand());
        o.setStatus(OrderStatus.ORDER_CREATED);
        orderRepository.save(o);
    }

    public void cancelProductOrder(String orderId){
        orderRepository.deleteById(orderId);
    }

    public void addProduct(String productId, String orderId){
        Order order = orderRepository.findById(orderId).get();
        if(order.getStatus() == OrderStatus.ORDER_CREATED){
            Product product = productRepository.findById(productId).get();
            order.addProduct(product);
            orderRepository.save(order);
        }
    }

    public void removeProduct(String productId, String orderId){
        Order order = orderRepository.findById(orderId).get();
        if(order.getStatus() == OrderStatus.ORDER_CREATED) {
            Product product = productRepository.findById(productId).get();
            order.removeProduct(product);
            orderRepository.save(order);
        }
    }

    public void sendPayProductOrderMessage(String orderId, String visitorId){
        double total = orderRepository.findById(orderId).get().getTotal();
        PayOrder po = new PayOrder(visitorId, orderId, total);
        productOrderSaga.payProductOrder(po);
    }

    public void markOrderAsPaid(String orderId){
        Order order = this.orderRepository.findById(orderId).get();
        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        this.orderRepository.save(order);
    }

}
