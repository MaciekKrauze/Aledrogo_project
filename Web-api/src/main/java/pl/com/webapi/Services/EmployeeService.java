package pl.com.webapi.Services;

import org.springframework.stereotype.Service;
import pl.com.data.Entities.Employee;
import pl.com.data.Repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(Long id) {
        return Optional.of(employeeRepository.findById(id).get());
    }
}
