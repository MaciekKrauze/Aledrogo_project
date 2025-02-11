package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ShippingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    private String type;
    private final LocalDate createdat = LocalDate.now();

    public ShippingPoint() {
    }

    public ShippingPoint(String name, Address address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

}
