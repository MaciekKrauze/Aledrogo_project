package pl.com.webapi.Services;

import org.springframework.stereotype.Service;
import pl.com.data.Entities.Address;
import pl.com.data.Repositories.AddressRepository;


@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void add(Address address) {
        addressRepository.save(address);
    }
}
