package be.ugent.productservice.adapters.rest;

import be.ugent.productservice.domain.Order;
import be.ugent.productservice.domain.OrderStatus;
import be.ugent.productservice.domain.Product;
import be.ugent.productservice.domain.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductRestController {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductRestController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts(){
        return productOrderService.getAllProducts();
    }

    @GetMapping("/allOrders")
    public List<Order> getAllOrders(){
        return productOrderService.getAllOrders();
    }

    @PostMapping("/createProductOrder")
    public void createProductOrder(@RequestBody Order order) {
        productOrderService.createProductOrder(order);
    }

    @PostMapping("/cancelProductOrder/{orderId}")
    public void cancelProductOrder(@PathVariable String orderId) {
        productOrderService.cancelProductOrder(orderId);
    }

    @PostMapping("/addProduct/{productId}/{orderId}")
    public void addProduct(@PathVariable String productId,
                           @PathVariable String orderId) {
        productOrderService.addProduct(productId, orderId);
    }

    @PostMapping("/removeProduct/{productId}/{orderId}")
    public void removeProduct(@PathVariable String productId,
                               @PathVariable String orderId) {
        productOrderService.removeProduct(productId, orderId);
    }

    @PostMapping("/payOrder/{orderId}/{visitorId}")
    public void sendPayProductOrder(@PathVariable String orderId, @PathVariable String visitorId){
        productOrderService.sendPayProductOrderMessage(orderId, visitorId);
    }

}
