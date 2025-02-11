package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.com.data.Entities.Address;
import pl.com.data.Repositories.AddressRepository;
import pl.com.webapi.Services.AddressService;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void shouldCallAddMethodOfAddressRepository_whenAddIsCalled() {
        Address address = new Address();

        addressService.add(address);

        verify(addressRepository).save(address);
    }
}