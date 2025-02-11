package pl.com.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.core.Controllers.General.LoginAndRegisterController;
import pl.com.webapi.Services.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class LoginAndRegisterControllerTest {

    @InjectMocks
    LoginAndRegisterController controller;

    @Mock
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testRegisterAsClient() throws Exception {
        mockMvc.perform(get("/register_as_client"))
                .andExpect(status().isOk())
                .andExpect(view().name("General/LoginAndRegistration/Register_as_client"));
    }

    @Test
    void testRegisterAsSeller() throws Exception {
        mockMvc.perform(get("/register_as_seller"))
                .andExpect(status().isOk())
                .andExpect(view().name("General/LoginAndRegistration/Register_as_seller"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("General/LoginAndRegistration/Login"));
    }

//    @Test
//    void testPostRegisterAsClient() throws Exception {
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        mockMvc.perform(post("/register_as_client")
//                        .param("firstName", "Test")
//                        .param("lastName", "User")
//                        .param("email", "test10@example.com")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login?registrationSuccess"));
//
//        verify(userService, times(1)).createUser(any());
//    }
//
//    @Test
//    void testPostRegisterAsSeller() throws Exception {
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        mockMvc.perform(post("/register_as_seller")
//                        .param("firstName", "Test")
//                        .param("lastName", "User")
//                        .param("email", "test10@example.com")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login?registrationSuccess"));
//
//        verify(userService, times(1)).createUser(any());
//    }
//
//    @Test
//    void testLogout() throws Exception {
//        mockMvc.perform(post("/logout"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/"));
//    }
}
