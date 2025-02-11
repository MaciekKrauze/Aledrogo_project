package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
