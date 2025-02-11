package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}