package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.category.id = :categoryId")
    List<Product> findByNameAndCategory(@Param("name") String name, @Param("categoryId") Long categoryId);


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> findByProductNameContainingIgnoreCase(@Param("productName") String productName);

    List<Product> findByUser(User user);

    Iterable<Product> findByName(String name);
}
