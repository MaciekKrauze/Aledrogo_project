package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Delivery;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUserId(Long userId);
}
