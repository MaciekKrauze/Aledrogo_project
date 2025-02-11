package pl.com.adminpanel;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.adminpanel.Controller.AdminController;
import pl.com.data.Entities.Song;
import pl.com.webapi.Services.SongService;
import pl.com.webapi.Services.UserService;
import pl.com.webapi.Services.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminPanelApplicationTests {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private SongService songService;

    @InjectMocks
    private AdminController adminController;

    @Test
    void testAdminPanelAdminGet() {
        Model model = new BindingAwareModelMap();
        HttpSession session = new MockHttpSession();

        String view = adminController.adminPanelAdmin(model, session);
        assertEquals("/Admin/Admin_panel", view);
        assertNull(model.getAttribute("users"));
        assertNull(model.getAttribute("products"));
        assertNull(model.getAttribute("songs"));
    }

    @Test
    void testAdminPanelAdminPost() {
        HttpSession session = new MockHttpSession();

        RedirectView view = adminController.adminPanelAdmin("users", session);
        assertEquals("/admin/adminPanel", view.getUrl());
        assertEquals("users", session.getAttribute("category"));
    }

    @Test
    void testAddSong() {
        Song song = new Song();
        song.setName("Test");
        song.setArtist("Artist");
        BindingResult result = new BeanPropertyBindingResult(song, "song");
        Model model = new BindingAwareModelMap();

        when(songService.getSongByNameAndArtist(song.getName(), song.getArtist())).thenReturn(null);

        String view = adminController.addSong(song, result, model, new MockHttpSession());
        assertEquals("redirect:/admin/adminPanel", view);

        verify(songService, times(1)).addSong(song);
    }

    @Test
    void testDeleteSongNotFound() {
        when(songService.getSongById(1L)).thenReturn(null);

        RedirectView view = adminController.deleteSong(1L, new MockHttpSession());
        assertEquals("/admin/adminPanel", view.getUrl());
        verify(songService, never()).delete(1L);
    }

    @Test
    void testDeleteSongSuccess() {
        Song song = new Song();
        song.setId(1L);
        when(songService.getSongById(1L)).thenReturn(song);

        RedirectView view = adminController.deleteSong(1L, new MockHttpSession());
        assertEquals("/admin/adminPanel", view.getUrl());
        verify(songService, times(1)).delete(1L);
    }


    @Test
    void testUpdateSong() {
        Song existingSong = new Song();
        existingSong.setName("Test Old");
        existingSong.setArtist("Artist Old");
        existingSong.setAlbum("Test Old Album");
        existingSong.setReleaseDate("Test Old Release Date");

        when(songService.getSongById(anyLong())).thenReturn(existingSong);
        HttpSession session = new MockHttpSession();

        RedirectView view = adminController.updateSong(1L, "Test New", "Artist New", null, null, session);
        assertEquals("/admin/adminPanel", view.getUrl());

        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);
        verify(songService, times(1)).update(eq(1L), songCaptor.capture());

        Song updatedSong = songCaptor.getValue();

        assertEquals("Test New", updatedSong.getName());
        assertEquals("Artist New", updatedSong.getArtist());
        assertEquals("Test Old Album", updatedSong.getAlbum()); // Oczekiwane: stare dane
        assertEquals("Test Old Release Date", updatedSong.getReleaseDate()); // Oczekiwane: stare dane
    }
}
