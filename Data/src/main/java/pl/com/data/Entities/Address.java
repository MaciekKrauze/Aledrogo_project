package pl.com.data.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private Integer streetNumber;
    private Integer apartmentNumber;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private final LocalDate createdat = LocalDate.now();

    public Address() {
    }

    public Address(String streetName, Integer streetNumber, Integer apartmentNumber, String postalCode,
                   String city, String state, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", apartmentNumber=" + apartmentNumber +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", createdat=" + createdat +
                '}';
    }
}
