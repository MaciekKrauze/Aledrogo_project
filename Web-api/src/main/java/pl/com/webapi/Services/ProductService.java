package pl.com.webapi.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import pl.com.data.Entities.Product;
import pl.com.data.Entities.User;
import pl.com.data.Repositories.ProductRepository;
import pl.com.webapi.Exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CachePut(value = "products", key = "#product.id")
    public Product add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#categoryId")
    public List<Product> searchProducts(String name, Long categoryId) {
        List<Product> products = productRepository.findByNameAndCategory(name, categoryId);
        for (Product product : products) {
            String productName = product.getName();
            product.setName(productName.toUpperCase());
        }
        return products;
    }

    @Cacheable(value = "products", key = "#name")
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    @Cacheable(value = "products", key = "#user.id")
    public List<Product> getProductsByUser(User user) {
        return productRepository.findByUser(user);
    }

    @CacheEvict(value = "products", key = "#id")
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Category");
        }
        productRepository.deleteById(id);
    }

    @Cacheable(value = "products")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "products", key = "#name")
    public Iterable<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            // Retrieve the existing product
            Product existingProduct = productOptional.get();

            // Update the fields
            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if (product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getImageId() != null) {
                existingProduct.setImageId(product.getImageId());
            }
            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }
            if (product.getSeller() != null) {
                existingProduct.setSeller(product.getSeller());
            }

            // Save the updated product to the repository
            productRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product with ID " + id + " not found");
        }
    }
}
