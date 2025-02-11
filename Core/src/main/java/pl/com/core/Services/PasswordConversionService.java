package pl.com.core.Services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.data.Entities.User;
import pl.com.data.Repositories.UserRepository;


import java.util.List;

@Service
public class PasswordConversionService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordConversionService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void encodePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getPassword().startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }
        }
    }
}
