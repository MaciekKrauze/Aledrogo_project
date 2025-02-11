package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.com.data.Entities.Delivery;
import pl.com.data.Repositories.DeliveryRepository;
import pl.com.webapi.Services.DeliveryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryService deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDeliveryByIdTest() {
        Delivery delivery = new Delivery();
        when(deliveryRepository.findById(anyLong())).thenReturn(Optional.of(delivery));

        Optional<Delivery> actual = deliveryService.getDeliveryById((long) 1);

        assertEquals(Optional.of(delivery), actual);
    }

    @Test
    public void getAllDeliveriesTest() {
        List<Delivery> expected = new ArrayList<>();
        expected.add(new Delivery());
        when(deliveryRepository.findByUserId(anyLong())).thenReturn(expected);

        List<Delivery> actual = deliveryService.getAllDeliveries((long) 1);

        assertEquals(expected, actual);
    }

    @Test
    public void getAllDeliveriesByIdTest() {
        List<Delivery> expected = new ArrayList<>();
        expected.add(new Delivery());
        when(deliveryRepository.findByUserId(anyLong())).thenReturn(expected);

        List<Delivery> actual = deliveryService.getAllDeliveriesById((long) 1);

        assertEquals(expected, actual);
    }

    @Test
    public void updateDeliveriesStatusTest() {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery delivery1 = new Delivery();
        delivery1.setIfDelivered(false);

        Delivery delivery2 = new Delivery();
        delivery2.setIfDelivered(false);

        deliveries.add(delivery1);
        deliveries.add(delivery2);

        when(deliveryRepository.findAll()).thenReturn(deliveries);

        deliveryService.updateDeliveriesStatus();

        verify(deliveryRepository, times(2)).save(any(Delivery.class));

        for(Delivery delivery : deliveries) {
            assertEquals(true, delivery.isIfDelivered());
        }
    }
}