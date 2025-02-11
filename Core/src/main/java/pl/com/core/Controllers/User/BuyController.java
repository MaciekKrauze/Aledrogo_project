package pl.com.core.Controllers.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.ShopHistory;
import pl.com.data.Entities.User;
import pl.com.webapi.Services.ProductService;
import pl.com.webapi.Services.UserService;

import java.util.Optional;

@Controller
public class BuyController {

    private final ProductService productService;
    private final UserService userService;

    public BuyController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/search")
    public RedirectView buy(HttpSession session, @RequestParam long productId) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return new RedirectView("/login");
        }

        Boolean userGuessAttr = (Boolean) session.getAttribute("userGuess");
        boolean hasDiscount = userGuessAttr != null && userGuessAttr;

        try {
            userService.purchaseProduct(userId, productId, hasDiscount);
            return new RedirectView("/search?success");
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("/search?error");
        }
    }
}