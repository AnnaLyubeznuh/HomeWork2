import org.example.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLesson10 {

    static ChromeDriver driver;
    static MainPage mainPage;

    @BeforeAll
    static void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);
        driver = new ChromeDriver(options);
        driver.get("https://www.mts.by/");
        sleep(300);

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        sleep(300);

        mainPage.scrollToPayBlock();
        sleep(300);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    // Задание №1
    @Test
    @Order(1)
    void testPlaceholdersConnectionTab() {
        mainPage.selectTab("Услуги связи");

        assertEquals("Номер телефона", mainPage.getPlaceholder(mainPage.getPhoneConnection()),
                "Placeholder поля телефона (Услуги связи) не совпадает");
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getSumConnection()),
                "Placeholder поля суммы (Услуги связи) не совпадает");
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getEmailConnection()),
                "Placeholder поля email (Услуги связи) не совпадает");

        System.out.println("Тест 1. Плейсхолдеры таба 'Услуги связи' корректны");
    }

    @Test
    @Order(2)
    void testPlaceholdersInternetTab() throws InterruptedException {
        mainPage.selectTab("Домашний интернет");
        sleep(300);

        assertEquals("Номер абонента", mainPage.getPlaceholder(mainPage.getPhoneInternet()),
                "Placeholder поля телефона (Домашний интернет) не совпадает");
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getSumInternet()),
                "Placeholder поля суммы (Домашний интернет) не совпадает");
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getEmailInternet()),
                "Placeholder поля email (Домашний интернет) не совпадает");

        System.out.println("Тест 2. Плейсхолдеры таба 'Домашний интернет' корректны");
    }

    @Test
    @Order(3)
    void testPlaceholdersInstalmentTab() throws InterruptedException {
        mainPage.selectTab("Рассрочка");
        sleep(300);

        assertEquals("Номер счета на 44", mainPage.getPlaceholder(mainPage.getScoreInstalment()),
                "Placeholder поля счёта (Рассрочка) не совпадает");
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getSumInstalment()),
                "Placeholder поля суммы (Рассрочка) не совпадает");
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getEmailInstalment()),
                "Placeholder поля email (Рассрочка) не совпадает");

        System.out.println("Тест 3. Плейсхолдеры таба 'Рассрочка' корректны");
    }

    @Test
    @Order(4)
    void testPlaceholdersArrearsTab() throws InterruptedException {
        mainPage.selectTab("Задолженность");
        sleep(300);

        assertEquals("Номер счета на 2073", mainPage.getPlaceholder(mainPage.getScoreArrears()),
                "Placeholder поля счёта (Задолженность) не совпадает");
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getSumArrears()),
                "Placeholder поля суммы (Задолженность) не совпадает");
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getEmailArrears()),
                "Placeholder поля email (Задолженность) не совпадает");

        System.out.println("Тест 4. Плейсхолдеры таба 'Задолженность' корректны");
    }

    // Задание №2
    @Test
    @Order(5)
    void testPaymentIFrame() throws InterruptedException {

        mainPage.selectTab("Услуги связи");
        sleep(300);
        mainPage.fillConnectionFormAndSubmit("297777777", "55", "ma@ma.ru");

        // Переключение на IFrame
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'bepaid.by')]")));

        sleep(2000);

        assertEquals("55.00 BYN", mainPage.getIFrameAmount(), "Сумма в iframe не совпадает");

        assertEquals("Оплатить 55.00 BYN", mainPage.getIFramePayButtonText(), "Сумма на кнопке оплаты не совпадает");

        assertEquals("Оплата: Услуги связи Номер:375297777777", mainPage.getIFramePhone(), "Номер телефона в iframe не совпадает");

        assertEquals("Номер карты", mainPage.getIFrameLabelText(mainPage.getIFrameLabelCardNumber()),
                "Label поля номера карты не совпадает");
        assertEquals("Срок действия", mainPage.getIFrameLabelText(mainPage.getIFrameLabelExpiry()),
                "Label поля срока действия не совпадает");
        assertEquals("CVC", mainPage.getIFrameLabelText(mainPage.getIFrameLabelCvc()),
                "Label поля CVC не совпадает");
        assertEquals("Имя и фамилия на карте", mainPage.getIFrameLabelText(mainPage.getIFrameLabelHolder()),
                "Label поля имени не совпадает");

        assertTrue(mainPage.isIFrameIconDisplayed(mainPage.getIFrameIconVisa()), "Иконка Visa не отображается");
        assertTrue(mainPage.isIFrameIconDisplayed(mainPage.getIFrameIconMastercard()), "Иконка MasterCard не отображается");
        assertTrue(mainPage.isIFrameIconDisplayed(mainPage.getIFrameIconBelkart()), "Иконка Белкарт не отображается");
        assertTrue(mainPage.isIFrameIconDisplayed(mainPage.getIFrameIconMaestro()), "Иконка Maestro не отображается");

        System.out.println("Тест 5. Проверки в iframe пройдены");
    }
}