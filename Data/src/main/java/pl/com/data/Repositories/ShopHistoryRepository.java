package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.ShopHistory;

@Repository
public interface ShopHistoryRepository extends JpaRepository<ShopHistory, Long> {
}
