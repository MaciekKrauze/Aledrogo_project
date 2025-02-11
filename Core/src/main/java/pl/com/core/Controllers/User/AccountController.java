package pl.com.core.Controllers.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import pl.com.data.Entities.Address;
import pl.com.data.Entities.User;
import pl.com.webapi.Services.AddressService;
import pl.com.webapi.Services.UserService;


@Controller
public class AccountController {

    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AccountController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/YourAledrogo")
    public String account() {
        return "/User/YourAledrogo";
    }

    @GetMapping("/User/YourAledrogo/Account")
    public String Account(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("userId");
        User user = userService.getById(id);
        Double balance = user.getBalance();
        model.addAttribute("balance", balance);
        Address adress = user.getAdress();
        model.addAttribute("adress", adress);


        return "/User/YourAledrogo/Account";
    }

//    @PostMapping("/User/Account/AddAddress")
//    public RedirectView AddAddress(@ModelAttribute Address address, HttpSession session) {
//
//        User user = userService.getById((Long) session.getAttribute("userId"));
//
//        Address adress1 = user.getAdress();
//        Long id = adress1.getId();
//
//        address.setId(id);
//        addressService.add(address);
//        return new RedirectView("/User/YourAledrogo/Account");
//    }

    @PostMapping("/User/Account/AddAddress")
    public RedirectView addOrUpdateAddress(@ModelAttribute Address address, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return new RedirectView("/login"); // lub odpowiednia ścieżka logowania
        }

        User user = userService.getById(userId);
        Address currentAddress = user.getAdress();

        if (currentAddress != null) {
            // Aktualizacja istniejącego adresu
            address.setId(currentAddress.getId());
        } else {
            // Ustawienie nowego adresu
            user.setAdress(address);
        }

        addressService.add(address);
        userService.update(userId, user);
        return new RedirectView("/User/YourAledrogo/Account");
    }


    @PostMapping("/User/YourAledrogo/Account/AddBalance")
    public RedirectView AddBalance(@RequestParam Double balance, HttpSession session) {
        User user = userService.getById((Long) session.getAttribute("userId"));

        balance += user.getBalance();
        user.setBalance(balance);

        userService.add(user);
        return new RedirectView("/User/YourAledrogo/Account");
    }
}
