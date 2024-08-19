package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.MtsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsSiteTest {

    private WebDriver driver;
    private MtsPage mtsPage;


    private static final String DRIVER_PATH = "/Users/darkldawn/Documents/GitHub/chromedriver";
    private static final String BASE_URL = "https://www.mts.by";


    private static final String TEST_PHONE = "297777777";
    private static final String TEST_SUM = "499";
    private static final String EXPECTED_MODAL_TITLE = "499.00 BYN";
    private static final String EXPECTED_INFO_TEXT = "Оплатить 499.00 BYN";
    private static final String EXPECTED_PHONE_FIELD = "375297777777";
    private static final String EXPECTED_PHONE_PLACEHOLDER = "Номер телефона";
    private static final String EXPECTED_SUM_PLACEHOLDER = "Сумма";
    private static final String EXPECTED_EMAIL_PLACEHOLDER = "E-mail для отправки чека";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        mtsPage = new MtsPage(driver);
        mtsPage.acceptCookies();
    }

    @Test
    public void testFilledFieldsAndModals() {
        mtsPage.fillContactInfo(TEST_PHONE, TEST_SUM);
        mtsPage.switchToIframe();
        assertTrue(mtsPage.getModalTitle().contains(EXPECTED_MODAL_TITLE), "The modal window contains test: " + EXPECTED_MODAL_TITLE);
        assertTrue(mtsPage.getInfoButtonText().contains(EXPECTED_INFO_TEXT), "The confirmation button contains text: " + EXPECTED_INFO_TEXT);
        assertTrue(mtsPage.getPhoneFieldText().contains("Оплата: Услуги связи Номер:" + EXPECTED_PHONE_FIELD), "Phone placeholder contains text: " + EXPECTED_PHONE_FIELD);
        assertTrue(mtsPage.isPaymentIconsDisplayed(), "Payment system icons should be displayed");
    }

    @Test
    public void testFilledFields() {
        selectDropdownOption("Communication services");
        checkFieldPlaceholders(EXPECTED_PHONE_PLACEHOLDER, EXPECTED_SUM_PLACEHOLDER, EXPECTED_EMAIL_PLACEHOLDER);
        selectDropdownOption("home internet");
        checkFieldPlaceholders(EXPECTED_PHONE_PLACEHOLDER, EXPECTED_SUM_PLACEHOLDER, EXPECTED_EMAIL_PLACEHOLDER);
        selectDropdownOption("Installment");
        checkFieldPlaceholders(EXPECTED_PHONE_PLACEHOLDER, EXPECTED_SUM_PLACEHOLDER, EXPECTED_EMAIL_PLACEHOLDER);
        selectDropdownOption("Debt");
        checkFieldPlaceholders(EXPECTED_PHONE_PLACEHOLDER, EXPECTED_SUM_PLACEHOLDER, EXPECTED_EMAIL_PLACEHOLDER);
    }

    public void selectDropdownOption(String optionText) {
    }

    public void checkFieldPlaceholders(String expectedPhonePlaceholder, String expectedAmountPlaceholder, String expectedEmailPlaceholder) {
        assertEquals(expectedPhonePlaceholder, mtsPage.getFieldPlaceholder("//*[@id=\"connection-phone\"]"), "Поле 1 должно содержать подсказку 'Номер телефона'");
        assertEquals(expectedAmountPlaceholder, mtsPage.getFieldPlaceholder("//*[@id=\"connection-sum\"]"), "Поле 2 должно содержать подсказку 'Сумма'");
        assertEquals(expectedEmailPlaceholder, mtsPage.getFieldPlaceholder("//*[@id=\"connection-email\"]"), "Поле 3 должно содержать подсказку 'E-mail для отправки чека'");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
