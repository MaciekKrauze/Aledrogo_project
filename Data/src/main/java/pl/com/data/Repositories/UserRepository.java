package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByFirstName(String firstName);
}
