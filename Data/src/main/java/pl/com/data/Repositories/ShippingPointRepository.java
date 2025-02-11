package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.ShippingPoint;

@Repository
public interface ShippingPointRepository extends JpaRepository<ShippingPoint, Long> {
    Iterable<ShippingPoint> findByName(String name);
}
