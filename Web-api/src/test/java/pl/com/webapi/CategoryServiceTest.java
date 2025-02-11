package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.com.data.Entities.Category;
import pl.com.data.Repositories.CategoryRepository;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Services.CategoryService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> expected = new ArrayList<>();
        expected.add(new Category());

        when(categoryRepository.findAll()).thenReturn(expected);

        List<Category> actual = categoryService.getAllCategories();

        assertEquals(expected, actual);
    }

    @Test
    public void addCategoryTest() {
        Category category = new Category();
        categoryService.add(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void deleteCategoryTest() {
        Category category = new Category();
        Long categoryId = category.getId();

        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.delete(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    public void deleteCategoryNotFoundTest() {
        Long categoryId = 1L;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> categoryService.delete(categoryId));
    }

    @Test
    public void getByIdCategoryTest() {
        Category category = new Category();
        Long categoryId = category.getId();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> actual = categoryService.getById(categoryId);

        assertEquals(Optional.of(category), actual);
    }

    @Test
    public void findByNameCategoryTest() {
        List<Category> expected = new ArrayList<>();
        expected.add(new Category());

        when(categoryRepository.findByCategoryName(anyString())).thenReturn(expected);

        List<Category> actual = (List<Category>) categoryService.findByName("Test");

        assertEquals(expected, actual);
    }

    @Test
    public void getCategoryByIdTest() {
        Category category = new Category();
        Long categoryId = category.getId();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(category));

        Category actual = categoryService.getCategoryById(categoryId);

        assertEquals(category, actual);
    }
}
