package pl.com.core.Controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.security.web.csrf.CsrfToken;

@RestController
@RequestMapping("/api")
public class CsrfTokenController {

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
