package pl.com.webapi.Services;

import org.springframework.stereotype.Service;
import pl.com.data.Repositories.ShopHistoryRepository;

@Service
public class ShopHistoryService {
    private final ShopHistoryRepository shopHistoryRepository;

    public ShopHistoryService(ShopHistoryRepository shopHistoryRepository) {
        this.shopHistoryRepository = shopHistoryRepository;
    }

}
