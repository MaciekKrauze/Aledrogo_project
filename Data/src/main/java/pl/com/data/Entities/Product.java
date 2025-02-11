package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Column(name = "image_id")
    private Long imageId;
    @ManyToOne
    private Category category;
    @OneToOne
    private User user;
    private final LocalDate createdat = LocalDate.now();

    public Product() {
    }

    public Product(String name, String description, Double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public Product(String name, String description, Double price, Category category, User seller) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.user = seller;
    }

    public Product(String name, String description, Double price, Long imageId, Category category, User seller) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.category = category;
        this.user = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSeller() {
        return user;
    }

    public void setSeller(User seller) {
        this.user = seller;
    }
}
