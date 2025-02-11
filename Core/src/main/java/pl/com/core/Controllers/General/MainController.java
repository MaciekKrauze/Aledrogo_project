package pl.com.core.Controllers.General;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;


import pl.com.data.Entities.Category;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.User;
import pl.com.webapi.Services.CategoryService;
import pl.com.webapi.Services.ProductService;
import pl.com.webapi.Services.UserService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, UserService userService){
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    String index(HttpSession session, Model model, @CurrentSecurityContext SecurityContext securityContext) {

        Optional<User> userByEmail = Optional.ofNullable(userService.findByEmail(securityContext.getAuthentication().getName()));
        GrantedAuthority authority = securityContext.getAuthentication().getAuthorities().iterator().next();

        userByEmail.ifPresent(user -> session.setAttribute("userId", user.getId()));

        Boolean ifSeller;

        if (authority.getAuthority().equals("ROLE_SELLER")){
            ifSeller = true;
            model.addAttribute("ifSeller", ifSeller);
        }
        model.addAttribute("userRole", authority.getAuthority());

        Iterable<Product> allProducts = productService.getAllProducts();

        List<Product> allProductsList = StreamSupport.stream(allProducts.spliterator(), false).collect(Collectors.toList());

        Collections.shuffle(allProductsList);

        List<Product> threeRandomProducts;
        if (allProductsList.size() >= 3) {
            threeRandomProducts = allProductsList.subList(0, 3);
        } else {
            // In case there are less than 3 items in the list
            threeRandomProducts = allProductsList;
        }

        model.addAttribute("products", threeRandomProducts);

        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "General/Main/index";
    }



    @GetMapping("/search")
    public String searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            Model model,
            HttpSession session) {
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("userGuess", session.getAttribute("userGuess"));
        List<Product> products;
        if (categoryId != null && name != null) {
            products = productService.searchProducts(name, categoryId);
        } else if (name != null) {
            products = productService.searchProductsByName(name);
        }
        else {
            products = List.of();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        return "General/Main/SearchResults";
    }



}
