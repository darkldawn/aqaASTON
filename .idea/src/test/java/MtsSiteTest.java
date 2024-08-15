import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MtsSiteTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
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
            assertTrue("Модальное окно должно содержать текст: " + вашОжидаемыйТекстСуммы,
                    modalTitle.getText().contains(вашОжидаемыйТекстСуммы));
        }

        private void checkInfo(String вашОжидаемыйТекстСуммы) {
            WebElement button2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".colored.disabled")));
            assertTrue("Кнопка подтверждения должна содержать текст: " + вашОжидаемыйТекстСуммы,
                    button2.getText().contains(вашОжидаемыйТекстСуммы));
        }

        private void checkPhoneField(String вашНомерТелефона) {
            WebElement phoneField = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]"));
            assertTrue("Поле телефона должно содержать: " + вашНомерТелефона,
                    phoneField.getText().contains("Оплата: Услуги связи Номер:" + вашНомерТелефона));
        }

        private void checkPaymentIcons() {
            WebElement paymentIcons = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]"));
            assertTrue("Должны быть отображены иконки платежных систем", paymentIcons.isDisplayed());
        }

        private void checkFieldPlaceholders(String срокДействия, String CVC, String имяДержателяКакНаКарте, String номерКарты) {
            WebElement label1 = driver.findElement(By.xpath("//label[contains(text(), 'Срок действия')]"));
            WebElement label2 = driver.findElement(By.xpath("//label[contains(text(), 'CVC')]"));
            WebElement label3 = driver.findElement(By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]"));
            WebElement label4 = driver.findElement(By.xpath("//label[contains(text(), 'Номер карты')]"));
            assertEquals("Поле срока действия должно содержать предусмотренную подсказку", срокДействия, label1.getText());
            assertEquals("Поле CVC должно содержать предусмотренную подсказку", CVC, label2.getText());
            assertEquals("Поле имени владельца должно содержать предусмотренную подсказку", имяДержателяКакНаКарте, label3.getText());
            assertEquals("Поле номера карты должно содержать предусмотренную подсказку", номерКарты, label4.getText());
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

            assertTrue("Поле 1 должно содержать подсказку 'Номер телефона'", field1.getAttribute("placeholder").equals(expectedPhonePlaceholder));
            assertTrue("Поле 2 должно содержать подсказку 'Сумма'", field2.getAttribute("placeholder").equals(expectedAmountPlaceholder));
            assertTrue("Поле 3 должно содержать подсказку 'E-mail для отправки чека'", field3.getAttribute("placeholder").equals(expectedEmailPlaceholder));
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

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

