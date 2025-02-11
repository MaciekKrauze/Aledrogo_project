package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.com.data.Entities.Department;
import pl.com.data.Repositories.DepartmentRepository;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Services.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addDepartment() {
        Department department = new Department();
        departmentService.add(department);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void deleteDepartment() {
        when(departmentRepository.existsById(anyLong())).thenReturn(true);
        departmentService.delete(1L);
        verify(departmentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteNonExistingDepartment() {
        when(departmentRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> departmentService.delete(1L));
    }

    @Test
    void getAllDepartments() {
        List<Department> expectedDepartments = new ArrayList<>();
        when(departmentRepository.findAll()).thenReturn(expectedDepartments);

        List<Department> actualDepartments = (List<Department>) departmentService.getAllDepartments();
        assertEquals(expectedDepartments, actualDepartments);
    }

    @Test
    void getById_existingDepartment() {
        Department expectedDepartment = new Department();
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(expectedDepartment));

        Department actualDepartment = departmentService.getById(1L);
        assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    void getById_nonExistingDepartment() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> departmentService.getById(1L));
    }

    @Test
    void findByName() {
        List<Department> expectedDepartments = new ArrayList<>();
        when(departmentRepository.findByDepartmentName(anyString())).thenReturn(expectedDepartments);

        List<Department> actualDepartments = (List<Department>) departmentService.findByName("DepartmentName");
        assertEquals(expectedDepartments, actualDepartments);
    }
}
