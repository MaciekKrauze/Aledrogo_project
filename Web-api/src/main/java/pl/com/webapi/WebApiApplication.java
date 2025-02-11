package pl.com.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.com"})
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
