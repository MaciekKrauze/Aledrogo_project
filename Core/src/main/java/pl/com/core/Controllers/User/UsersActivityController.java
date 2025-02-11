package pl.com.core.Controllers.User;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.com.data.Entities.*;
import pl.com.webapi.Services.DeliveryService;
import pl.com.webapi.Services.ProductService;
import pl.com.webapi.Services.ShopHistoryService;
import pl.com.webapi.Services.UserService;


import java.util.List;

@Controller
public class UsersActivityController {

    private final DeliveryService deliveryService;
    private final UserService userService;
    private final ShopHistoryService shopHistoryService;
    private final ProductService productService;

    @Autowired
    public UsersActivityController(DeliveryService deliveryService, UserService userService, ShopHistoryService shopHistoryService, ProductService productService) {
        this.deliveryService = deliveryService;
        this.userService = userService;
        this.shopHistoryService = shopHistoryService;
        this.productService = productService;
    }

    @GetMapping("/User/Delivery")
    public String deliveries(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<Delivery> allDeliveries = deliveryService.getAllDeliveriesById(userId);
        for (Delivery delivery : allDeliveries) {
            System.out.println(delivery.getId());
        }
        model.addAttribute("allDeliveries", allDeliveries);
        return "User/Delivery";
    }

    @Transactional
    @GetMapping("/User/YourAledrogo/History")
    public String history(Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        User user = userService.getById(userId);
        ShopHistory history = user.getHistory();

        List<Product> productsList = history.getProductsList();
        if (productsList.isEmpty()) {
            System.out.println("ups");
        }
        else{
            for (Product product : productsList) {
                System.out.println(product.getId());
                System.out.println(product.getName());
            }
        }

        model.addAttribute("productsList", productsList);
        return "User/History";
    }
}

