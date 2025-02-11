package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;
    private boolean ifDelivered=false;
    private final LocalDate createdat = LocalDate.now();

    public Delivery() {
    }

    public Delivery(Product product, User user, boolean ifDelivered) {
        this.product = product;
        this.user = user;
        this.ifDelivered = ifDelivered;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isIfDelivered() {
        return ifDelivered;
    }

    public void setIfDelivered(boolean ifDelivered) {
        this.ifDelivered = ifDelivered;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
