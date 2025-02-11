package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ShopHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "shop_history_product_list",
            joinColumns = @JoinColumn(name = "shop_history_id"),
            inverseJoinColumns = @JoinColumn(name = "product_list_id")
    )
    private List<Product> productList;
    private final LocalDate createdat = LocalDate.now();

    public ShopHistory() {
    }

    public ShopHistory(List<Product> productList) {
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductsList() {
        return productList;
    }

    public void setProductsList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

}