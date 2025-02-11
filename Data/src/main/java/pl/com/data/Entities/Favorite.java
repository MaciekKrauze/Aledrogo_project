package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    List<Product> products;
    private final LocalDate createdat = LocalDate.now();

    public Favorite() {}

    public Favorite(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

}
