package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
