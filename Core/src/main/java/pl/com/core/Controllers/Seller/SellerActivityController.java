package pl.com.core.Controllers.Seller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import pl.com.data.Entities.Category;
import pl.com.data.Entities.Image;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.User;
import pl.com.webapi.Services.CategoryService;
import pl.com.webapi.Services.ImageService;
import pl.com.webapi.Services.ProductService;
import pl.com.webapi.Services.UserService;


import java.io.IOException;
import java.util.List;

@Controller
public class SellerActivityController {


    private final ProductService productService;
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public SellerActivityController(ProductService productService,
                                    ImageService imageService,
                                    CategoryService categoryService,
                                    UserService userService) {
        this.productService = productService;
        this.imageService = imageService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/Seller/MyProducts")
    public String MyProducts(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.getById(userId);
        List<Product> products = productService.getProductsByUser(user);
        model.addAttribute("products", products);
        return "Seller/My_products";
    }

    @GetMapping("/Seller/Sell")
    public String Sell(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("allCategories", categories);
        return "Seller/AddProduct";
    }


    @PostMapping("/Seller/Sell/NewProduct")
    public RedirectView AddProduct(@ModelAttribute Product product,
                                   @RequestParam("category") Long categoryId,
                                   @RequestParam("image") MultipartFile file,
                                   HttpSession session,
                                   Model model) {
        System.out.println(categoryId);
        try {
            Category category = categoryService.getCategoryById(categoryId);
            product.setCategory(category);

            Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageService.saveImage(image);

            product.setImageId(image.getId());
            User user = userService.getById((Long) session.getAttribute("userId"));
            product.setSeller(user);
            productService.add(product);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Nie udało się przesłać obrazu.");
            return new RedirectView("/Seller/Sell");
        }

        return new RedirectView("/Seller/Sell");
    }


}