package src.test.java;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MtsPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private static final String COOKIE_BUTTON_XPATH = "//*[@id=\"cookie-agree\"]";
    private static final String PHONE_FIELD_XPATH = "//*[@id=\"connection-phone\"]";
    private static final String SUM_FIELD_XPATH = "//*[@id=\"connection-sum\"]";
    private static final String PAYMENT_BUTTON_XPATH = "//*[@id=\"pay-connection\"]/button";

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COOKIE_BUTTON_XPATH))).click();
        } catch (Exception e) {
            System.out.println("Cookie acceptance button wasn't found");
        }
    }

    public void fillContactInfo(String phone, String sum) {
        driver.findElement(By.xpath(PHONE_FIELD_XPATH)).sendKeys(phone);
        driver.findElement(By.xpath(SUM_FIELD_XPATH)).sendKeys(sum);
        driver.findElement(By.xpath(PAYMENT_BUTTON_XPATH)).click();
    }

    public void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));
        driver.switchTo().frame(iframe);
    }

    public String getModalTitle() {
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
        return modalTitle.getText();
    }

    public String getInfoButtonText() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".colored.disabled")));
        return button.getText();
    }

    public String getPhoneFieldText() {
        WebElement phoneField = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]"));
        return phoneField.getText();
    }

    public boolean isPaymentIconsDisplayed() {
        WebElement paymentIcons = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]"));
        return paymentIcons.isDisplayed();
    }

    public String getFieldPlaceholder(String xpath) {
        return driver.findElement(By.xpath(xpath)).getAttribute("placeholder");
    }
}
