package pl.com.webapi.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.com.data.Entities.*;

import pl.com.data.Repositories.UserRepository;
import pl.com.webapi.Exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductService productService;

    public UserService(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByName(String name) {
        return (List<User>) userRepository.findByFirstName(name);
    }

    public List<User> getUsersList() {
        return (List<User>) userRepository.findAll();
    }

    public void update(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getBalance() != null) {
                existingUser.setBalance(user.getBalance());
            }
            if (user.getAddress() != null) {
                existingUser.setAddress(user.getAddress());
            }
            if (user.getIfAdult() != null) {
                existingUser.setIfAdult(user.getIfAdult());
            }
            if (user.getIfSeller() != null) {
                existingUser.setIfSeller(user.getIfSeller());
            }
            if (user.getFavoriteList() != null) {
                existingUser.setFavoriteList(user.getFavoriteList());
            }
            if (user.getHistory() != null) {
                existingUser.setHistory(user.getHistory());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }

            userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User");
        }
        userRepository.deleteById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (Boolean.TRUE.equals(user.getIfSeller())) {
            user.setRole("SELLER");
        } else {
            user.setRole("USER");
        }

        if (user.getHistory() == null) {
            user.setHistory(new ShopHistory());
        }
        if (user.getAddress() == null) {
            user.setAddress(new Address());
        }
        if (user.getFavoriteList() == null) {
            user.setFavoriteList(new Favorite());
        }

        return userRepository.save(user);
    }

    @Transactional
    public void purchaseProduct(Long userId, Long productId, boolean hasDiscount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productService.getById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        double price = product.getPrice();
        if (hasDiscount) {
            price *= 0.9;
        }

        if (user.getBalance() >= price) {
            user.setBalance(user.getBalance() - price);

            ShopHistory history = user.getHistory();
            if (history == null) {
                history = new ShopHistory();
                user.setHistory(history);
            }

            if (history.getProductsList() == null) {
                history.setProductsList(new ArrayList<>());
            }

            if (!history.getProductsList().contains(product)) {
                history.getProductsList().add(product);
            }

            userRepository.save(user);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }
}

