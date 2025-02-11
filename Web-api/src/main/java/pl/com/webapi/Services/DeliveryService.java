package pl.com.webapi.Services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.com.data.Entities.Delivery;
import pl.com.data.Repositories.DeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    public List<Delivery> getAllDeliveries(Long userId) {
        return deliveryRepository.findByUserId(userId);
    }

    public List<Delivery> getAllDeliveriesById(Long userId) {
        return deliveryRepository.findByUserId(userId);
    }

    @Scheduled(fixedRate = 300000)  // 5 minutes in milliseconds
    public void updateDeliveriesStatus() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        for (Delivery delivery : deliveries) {
            if (!delivery.isIfDelivered()) {
                delivery.setIfDelivered(true);
                deliveryRepository.save(delivery);
            }
        }
    }
}