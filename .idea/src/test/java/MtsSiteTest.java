import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsSiteTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/darkldawn/Documents/GitHub/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.mts.by");
        try {
            waitForElement(By.xpath("//*[@id=\"cookie-agree\"]"));
            WebElement button1 = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
            button1.click();
        } catch (TimeoutException e) {
            System.out.println("Кнопка согласия с куки не найдена.");
        }
    }

        @Test
        public void testFilledFieldsAndModals() {
            fillContactInfo("297777777", "499");
            checkPaymentWindow("499.00 BYN");
            checkInfo("Оплатить 499.00 BYN");
            checkPhoneField("375297777777");
            checkPaymentIcons();
            checkFieldPlaceholders("Срок действия", "CVC", "Имя держателя (как на карте)", "Номер карты");
        }

        private void fillContactInfo(String вашНомерТелефона, String Сумма) {
            driver.findElement(By.xpath("//*[@id=\"connection-phone\"]")).sendKeys(вашНомерТелефона);
            driver.findElement(By.xpath("//*[@id=\"connection-sum\"]")).sendKeys(Сумма);
            driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button")).click();

            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));
            driver.switchTo().frame(iframe);
        }

        private void checkPaymentWindow(String вашОжидаемыйТекстСуммы) {
            WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
            assertTrue(modalTitle.getText().contains(вашОжидаемыйТекстСуммы),
                    "Модальное окно должно содержать текст: " + вашОжидаемыйТекстСуммы);
        }

        private void checkInfo(String вашОжидаемыйТекстСуммы) {
            WebElement button2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".colored.disabled")));
            assertTrue(button2.getText().contains(вашОжидаемыйТекстСуммы),
                    "Кнопка подтверждения должна содержать текст: " + вашОжидаемыйТекстСуммы);
        }

        private void checkPhoneField(String вашНомерТелефона) {
            WebElement phoneField = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]"));
            assertTrue(phoneField.getText().contains("Оплата: Услуги связи Номер:" + вашНомерТелефона),
                    "Поле телефона должно содержать: " + вашНомерТелефона);
        }

        private void checkPaymentIcons() {
            WebElement paymentIcons = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]"));
            assertTrue(paymentIcons.isDisplayed(), "Должны быть отображены иконки платежных систем");
        }

        private void checkFieldPlaceholders(String срокДействия, String CVC, String имяДержателяКакНаКарте, String номерКарты) {
            WebElement label1 = driver.findElement(By.xpath("//label[contains(text(), 'Срок действия')]"));
            WebElement label2 = driver.findElement(By.xpath("//label[contains(text(), 'CVC')]"));
            WebElement label3 = driver.findElement(By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]"));
            WebElement label4 = driver.findElement(By.xpath("//label[contains(text(), 'Номер карты')]"));
            assertEquals(срокДействия, label1.getText(), "Срок действия ");
            assertEquals(CVC, label2.getText(), "CVC");
            assertEquals(имяДержателяКакНаКарте, label3.getText(), "Имя держателя(как на карте)");
            assertEquals(номерКарты, label4.getText(), "Номер карты");
        }

        @Test
        public void testFilledFields() {
            selectDropdownOption("Услуги связи");
            checkFieldPlaceholders("Номер телефона", "Сумма", "E-mail для отправки чека");

            selectDropdownOption("Домашний интернет");
            checkFieldPlaceholders("Номер телефона", "Сумма", "E-mail для отправки чека");

            selectDropdownOption("Рассрочка");
            checkFieldPlaceholders("Номер телефона", "Сумма", "E-mail для отправки чека");

            selectDropdownOption("Задолженность");
            checkFieldPlaceholders("Номер телефона", "Сумма", "E-mail для отправки чека");
        }

        private void selectDropdownOption(String optionText) {
            waitForElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
            WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
            dropdown.click();

            waitForElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]/p"));
            WebElement option = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]/p"));
            option.click();
        }

        private void checkFieldPlaceholders(String expectedPhonePlaceholder, String expectedAmountPlaceholder, String expectedEmailPlaceholder) {
            waitForElement(By.xpath("//*[@id=\"connection-phone\"]"));
            WebElement field1 = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));
            waitForElement(By.xpath("//*[@id=\"connection-sum\"]"));
            WebElement field2 = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
            waitForElement(By.xpath("//*[@id=\"connection-email\"]"));
            WebElement field3 = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));

            assertTrue(field1.getAttribute("placeholder").equals(expectedPhonePlaceholder), "Поле 1 должно содержать подсказку 'Номер телефона'");
            assertTrue(field2.getAttribute("placeholder").equals(expectedAmountPlaceholder), "Поле 2 должно содержать подсказку 'Сумма'");
            assertTrue(field3.getAttribute("placeholder").equals(expectedEmailPlaceholder), "Поле 3 должно содержать подсказку 'E-mail для отправки чека'");
        }

        private void waitForElement(By by) {
            int attempts = 0;
            while (attempts < 10) {
                try {
                    if (driver.findElement(by).isDisplayed()) {
                        return;
                    }
                } catch (Exception e) {
                }
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @AfterEach
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

