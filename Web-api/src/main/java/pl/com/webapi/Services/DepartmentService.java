package pl.com.webapi.Services;

import org.springframework.stereotype.Service;
import pl.com.data.Entities.Department;
import pl.com.data.Repositories.DepartmentRepository;
import pl.com.webapi.Exceptions.NotFoundException;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void add(Department department) {
        departmentRepository.save(department);
    }

    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException("Department");
        }
        departmentRepository.deleteById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department"));
    }

    public List<Department> findByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }
}
