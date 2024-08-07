import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

 public class MtsAutoTest {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/darkldawn/Documents/GitHub/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://mts.by");
        WebElement button = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
        button.click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBlockName() {
        WebElement blockElement = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2"));
        String blockTitle = blockElement.getText();
        if (blockTitle != null) {
            assertEquals("Онлайн пополнение\nбез комиссии", blockTitle);
        } else {
            fail("Название блока не найдено");
        }
    }

    @Test
    public void testPaymentSystemLogos() {
        int logoCount = driver.findElements(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul")).size();
        assertTrue(logoCount > 0);
    }

    @Test
    public void testLinkToServiceDetails() {
        WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a"));
        linkElement.click();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                driver.getCurrentUrl());

    }

    @Test
    public void testContinueButton() {

        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));
        phoneField.sendKeys("297777777");

        WebElement amountField = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        amountField.sendKeys("450");

        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();
        assertTrue(driver.getCurrentUrl().contains("https://www.mts.by"));
    }
}