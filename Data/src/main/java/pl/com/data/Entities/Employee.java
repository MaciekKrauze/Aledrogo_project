package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Double salary;
    private String position;
    private Integer accesLevel;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    private final LocalDate createdat = LocalDate.now();


    public Employee() {}

    public Employee(String firstName, String lastName, String email, String password, String phone, Double salary,
                    String position, Integer accesLevel, Department department, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.salary = salary;
        this.position = position;
        this.accesLevel = accesLevel;
        this.department = department;
        this.address = address;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

    public Integer getAccesLevel() {
        return accesLevel;
    }

    public void setAccesLevel(Integer accesLevel) {
        this.accesLevel = accesLevel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
