package src.main.java;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private WebDriver driver;


    private By cookiesAgreementButton = By.id("cookie-agree");
    private By blockTitleLocator = By.xpath("//div[@id='pay-section']//h2");
    private By paymentSystemLogosLocator = By.xpath("//div[@id='pay-section']//h2");
    private By serviceDetailsLink = By.linkText("Подробнее о сервисе");
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By continueButton = By.xpath("//*[@id=\"pay-connection\"]/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void agreeToCookies() {
        driver.findElement(cookiesAgreementButton).click();
    }

    public String getBlockTitle() {
        return driver.findElement(blockTitleLocator).getText();
    }

    public int getPaymentSystemLogoCount() {
        List<WebElement> logos = driver.findElements(paymentSystemLogosLocator);
        return logos.size();
    }

    public void clickServiceDetailsLink() {
        driver.findElement(serviceDetailsLink).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void enterSum(String sum) {
        driver.findElement(sumInput).sendKeys(sum);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }
}