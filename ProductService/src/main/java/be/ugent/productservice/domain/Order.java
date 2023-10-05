package be.ugent.productservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String visitorId;
    private List<Product> products;
    private OrderStatus status;
    private double total;
    private FestivalStand festivalStand;

    public Order() {
    }

    public Order(String visitorId, List<Product> products, FestivalStand festivalStand) {
        this.visitorId = visitorId;
        this.products = products;
        this.total = this.calculateTotal();
        this.festivalStand = festivalStand;
    }

    public Order(String id, String visitorId, List<Product> products, FestivalStand festivalStand) {
        this.id = id;
        this.visitorId = visitorId;
        this.products = products;
        this.status = OrderStatus.ORDER_CREATED;
        this.total = this.calculateTotal();
        this.festivalStand = festivalStand;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public List<Product> getProducts() {
        return products;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    private void setTotal(double total) {
        this.total = total;
    }

    public FestivalStand getFestivalStand() {
        return festivalStand;
    }

    public void setFestivalStand(FestivalStand festivalStand) {
        this.festivalStand = festivalStand;
    }

    private double calculateTotal(){
        double sum = 0.0;
        for (Product p :products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void addProduct(Product product){
        this.products.add(product);
        this.total += product.getPrice();
        this.total = (double) Math.round(this.total * 100) / 100;
    }

    public void removeProduct(Product product){
        this.products.removeIf(p -> {
            boolean remove = p.getId() == product.getId();
            if(remove){
                this.total -= product.getPrice();
                this.total = (double) Math.round(this.total * 100) / 100;
            }
            return remove;
        });
    }
}
