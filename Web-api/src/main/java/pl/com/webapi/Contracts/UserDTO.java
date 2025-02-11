package pl.com.webapi.Contracts;


public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean ifAdult;
    private Boolean ifSeller;
    private Double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
