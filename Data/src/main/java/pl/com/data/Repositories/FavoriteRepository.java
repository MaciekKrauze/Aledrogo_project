package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
