package pl.com.adminpanel;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminPanelTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maciej\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    void testAddSong() {
        driver.get("http://localhost:8080/admin/adminPanel");

        Select categoryDropdown = new Select(driver.findElement(By.name("category")));
        categoryDropdown.selectByValue("songs");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement addSongButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Dodaj piosenkę')]/following-sibling::span")));
        addSongButton.click();

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameInput.sendKeys("Test Song");

        WebElement albumInput = driver.findElement(By.name("album"));
        albumInput.sendKeys("Test Album");

        WebElement artistInput = driver.findElement(By.name("artist"));
        artistInput.sendKeys("Test Artist");

        WebElement releaseDateInput = driver.findElement(By.name("releaseDate"));
        releaseDateInput.sendKeys("01/01/2023");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Assert redirection to admin panel
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/adminPanel"));
        assertEquals("http://localhost:8080/admin/adminPanel", driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testSongExists() {
        driver.get("http://localhost:8080/admin/adminPanel");

        // Validate the added song appears in the list
        String songDetailsXpath = "//h3[contains(text(),'Test Song')]";
        WebElement songDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(songDetailsXpath)));

        assertNotNull(songDetails);
        assertTrue(songDetails.getText().contains("Test Song"));
    }

    @Test
    @Order(3)
    void testUpdateSong() {
        driver.get("http://localhost:8080/admin/adminPanel");

        // Debugging: Print page source
        System.out.println(driver.getPageSource());

        // Locate and click the update button for the added song
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Test Song')]/following-sibling::div/form[@action='/admin/song/update']/input[@type='submit']")));
        updateButton.click();
        System.out.println("test");
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameInput.clear();
        nameInput.sendKeys("Updated Test Song");

        WebElement albumInput = driver.findElement(By.name("album"));
        albumInput.clear();
        albumInput.sendKeys("Updated Test Album");

        WebElement artistInput = driver.findElement(By.name("artist"));
        artistInput.clear();
        artistInput.sendKeys("Updated Test Artist");

        WebElement releaseDateInput = driver.findElement(By.name("releaseDate"));
        releaseDateInput.clear();
        releaseDateInput.sendKeys("02/02/2023");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/adminPanel"));
        assertEquals("http://localhost:8080/admin/adminPanel", driver.getCurrentUrl());

        String updatedSongDetailsXpath = "//h3[contains(text(),'Updated Test Song')]";
        WebElement updatedSongDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedSongDetailsXpath)));

        assertNotNull(updatedSongDetails);
        assertTrue(updatedSongDetails.getText().contains("Updated Test Song"));
    }

    @Test
    @Order(4)
    void testDeleteSong() {
        driver.get("http://localhost:8080/admin/adminPanel");

        // Locate and click the delete button for the added song
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Test Song')]/following-sibling::div/form[@action='/admin/delete/song']/input[@type='submit']")));
        deleteButton.click();

        // Assert redirection to admin panel
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/adminPanel"));
        assertEquals("http://localhost:8080/admin/adminPanel", driver.getCurrentUrl());

        // Validate the song is no longer in the list
        String songDetailsXpath = "//h3[contains(text(),'Test Song')]";
        assertTrue(driver.findElements(By.xpath(songDetailsXpath)).isEmpty());
    }


    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
