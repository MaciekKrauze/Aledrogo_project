package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private Double balance = 0.0;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    private Boolean ifAdult=true;
    private Boolean ifSeller = false;

    @OneToOne(cascade = CascadeType.ALL)
    private Favorite favoriteList;
    @OneToOne(cascade = CascadeType.ALL)
    private ShopHistory history;

    String role;

    private final LocalDate createdat = LocalDate.now();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Double balance,
                Address address, Boolean ifAdult, Boolean ifSeller, Favorite favoriteList,
                ShopHistory history, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.address = address;
        this.ifAdult = ifAdult;
        this.ifSeller = ifSeller;
        this.favoriteList = favoriteList;
        this.history = history;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String password, Double balance, Address address,
                Boolean ifAdult, Boolean ifSeller, Favorite favoriteList, ShopHistory history) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.address = address;
        this.ifAdult = ifAdult;
        this.ifSeller = ifSeller;
        this.favoriteList = favoriteList;
        this.history = history;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public Boolean getIfAdult() {
        return ifAdult;
    }

    public void setIfAdult(Boolean ifAdult) {
        this.ifAdult = ifAdult;
    }

    public Boolean getIfSeller() {
        return ifSeller;
    }

    public void setIfSeller(Boolean ifSeller) {
        this.ifSeller = ifSeller;
    }

    public ShopHistory getHistory() {
        return history;
    }

    public void setHistory(ShopHistory history) {
        this.history = history;
    }

    public Favorite getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(Favorite favoriteList) {
        this.favoriteList = favoriteList;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}

