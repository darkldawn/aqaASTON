package src.main.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsAutoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    private final String chromeDriverPath = "/Users/darkldawn/Documents/GitHub/chromedriver";
    private final String targetUrl = "https://www.mts.by";
    private final String expectedBlockTitle = "Онлайн пополнение\nбез комиссии";
    private final String testPhoneNumber = "297777777";
    private final String testSum = "499";
    private final String expectedUrlAfterClick = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(targetUrl);

        mainPage = new MainPage(driver);
        mainPage.agreeToCookies();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBlockName() {
        String blockTitle = mainPage.getBlockTitle();
        Assertions.assertNotNull(blockTitle, "Название блока не найдено");
        assertEquals(expectedBlockTitle, blockTitle);
    }

    @Test
    public void testPaymentSystemLogos() {
        int logoCount = mainPage.getPaymentSystemLogoCount();
        assertTrue(logoCount > 0, "Логотипы платежных систем не найдены.");
    }

    @Test
    public void testLinkToServiceDetails() {
        mainPage.clickServiceDetailsLink();
        assertEquals(expectedUrlAfterClick, driver.getCurrentUrl());
    }

    @Test
    public void testContinueButton() {
        mainPage.enterPhone(testPhoneNumber);
        mainPage.enterSum(testSum);
        mainPage.clickContinueButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe"))); // Замените на реальный класс iframe
        assertTrue(driver.findElement(By.className("bepaid-iframe")).isDisplayed(), "Frame did not open correctly.");
    }
}