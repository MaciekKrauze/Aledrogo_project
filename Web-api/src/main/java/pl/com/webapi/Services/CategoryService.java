package pl.com.webapi.Services;

import org.springframework.stereotype.Service;
import pl.com.data.Entities.Category;
import pl.com.data.Repositories.CategoryRepository;
import pl.com.webapi.Exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        List<Category> resultList = new ArrayList<>();
        Iterable<Category> iterable = categoryRepository.findAll();
        if (iterable != null) {
            iterable.forEach(resultList::add);
        }
        return resultList;
    }

    public void add(Category category) {
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category");
        }
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public Iterable<Category> findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
