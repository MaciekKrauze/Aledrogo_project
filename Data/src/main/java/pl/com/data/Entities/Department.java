package pl.com.data.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String description;
    private final LocalDate createdat = LocalDate.now();


    public Department() {
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedat() {
        return createdat;
    }

}
