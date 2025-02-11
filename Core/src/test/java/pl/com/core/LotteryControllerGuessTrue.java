package pl.com.core;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.core.Components.SeedScheduler;
import pl.com.core.Controllers.General.LoteryController;
import pl.com.webapi.Services.SpotifyService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LotteryControllerGuessTrue {

    @Test
    void testLoteryUserGuessTrue() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        when(session.getAttribute("userGuess")).thenReturn(true);

        LoteryController loteryController = new LoteryController(mock(SeedScheduler.class), mock(SpotifyService.class));

        // Act
        String result = loteryController.lotery(model, session);

        // Assert
        verify(model).addAttribute("userGuess", true);
        assertEquals("General/Main/Lotery", result);
    }

    @Test
    void testLoteryUserGuessFalse() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        when(session.getAttribute("userGuess")).thenReturn(false);

        LoteryController loteryController = new LoteryController(mock(SeedScheduler.class), mock(SpotifyService.class));

        // Act
        String result = loteryController.lotery(model, session);

        // Assert
        verify(model).addAttribute("userGuess", false);
        assertEquals("General/Main/Lotery", result);
    }

    @Test
    void testLoteryUserGuessNull() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        when(session.getAttribute("userGuess")).thenReturn(null);

        LoteryController loteryController = new LoteryController(mock(SeedScheduler.class), mock(SpotifyService.class));

        // Act
        String result = loteryController.lotery(model, session);

        // Assert
        verify(model).addAttribute("userGuess", null);
        assertEquals("General/Main/Lotery", result);
    }

    @Test
    void testLoteryCorrectGuess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        SeedScheduler seedScheduler = mock(SeedScheduler.class);
        SpotifyService spotifyService = mock(SpotifyService.class);

        ArrayList<String> ladyGagaSongsList = new ArrayList<>();
        ladyGagaSongsList.add("Poker Face");
        when(spotifyService.getTracksByArtist("Lady Gaga")).thenReturn(ladyGagaSongsList);
        when(seedScheduler.getSeed()).thenReturn(0L);

        LoteryController loteryController = new LoteryController(seedScheduler, spotifyService);
        String userGuess = "Poker Face";

        // Act
        RedirectView result = loteryController.lotery(model, session, userGuess);

        // Assert
        verify(session).setAttribute("userGuess", true);
        assertEquals("/Lotery", result.getUrl());
    }

    @Test
    void testLoteryIncorrectGuess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        SeedScheduler seedScheduler = mock(SeedScheduler.class);
        SpotifyService spotifyService = mock(SpotifyService.class);

        ArrayList<String> ladyGagaSongsList = new ArrayList<>();
        ladyGagaSongsList.add("Poker Face");
        when(spotifyService.getTracksByArtist("Lady Gaga")).thenReturn(ladyGagaSongsList);
        when(seedScheduler.getSeed()).thenReturn(0L);

        LoteryController loteryController = new LoteryController(seedScheduler, spotifyService);
        String userGuess = "Bad Romance";

        // Act
        RedirectView result = loteryController.lotery(model, session, userGuess);

        // Assert
        verify(session).setAttribute("userGuess", false);
        assertEquals("/Lotery", result.getUrl());
    }


}
