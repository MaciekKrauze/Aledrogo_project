package pl.com.core.Controllers.General;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import pl.com.data.Entities.User;
import pl.com.webapi.Services.UserService;

@Controller
public class LoginAndRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginAndRegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register_as_client")
    public String registerAsClient() {
        logger.info("Navigating to register as client page.");
        return "General/LoginAndRegistration/Register_as_client";
    }

    @GetMapping("/register_as_seller")
    public String registerAsSeller() {
        logger.info("Navigating to register as seller page.");
        return "General/LoginAndRegistration/Register_as_seller";
    }

    @GetMapping("/login")
    public String login() {
        logger.info("Navigating to login page.");
        return "General/LoginAndRegistration/Login";
    }

    @PostMapping("/register_as_client")
    public RedirectView registerClient(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam(required = false, defaultValue = "false") boolean ifAdult,
                                       HttpSession session) {
        logger.info("Attempting to register client with email: {}", email);

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setIfSeller(false);
            userService.createUser(user);

            logger.info("Client registered successfully with email: {}", email);
            return new RedirectView("/login?registrationSuccess");
        } catch (IllegalArgumentException e) {
            logger.warn("Registration failed - email already used: {}", email);
            session.setAttribute("usedEmail", true);
            return new RedirectView("/register_as_client");
        } catch (Exception e) {
            logger.error("An error occurred during client registration: {}", e.getMessage());
            e.printStackTrace();
            return new RedirectView("/register_as_client?error");
        }
    }

    @PostMapping("/register_as_seller")
    public RedirectView registerSeller(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam(required = false, defaultValue = "false") boolean ifAdult,
                                       HttpSession session) {
        logger.info("Attempting to register seller with email: {}", email);

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setIfSeller(true);
            userService.createUser(user);

            logger.info("Seller registered successfully with email: {}", email);
            return new RedirectView("/login?registrationSuccess");
        } catch (IllegalArgumentException e) {
            logger.warn("Registration failed - email already used: {}", email);
            session.setAttribute("usedEmail", true);
            return new RedirectView("/register_as_client");
        } catch (Exception e) {
            logger.error("An error occurred during seller registration: {}", e.getMessage());
            e.printStackTrace();
            return new RedirectView("/register_as_seller?error");
        }
    }

    @PostMapping("/logout")
    public RedirectView logout() {
        logger.info("Logging out user.");
        return new RedirectView("/");
    }
}
