package pl.com.core.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {


    @GetMapping("/RandomSite")
    public String RandomSite() {
        return "/General/RandomSite";
    }

}
