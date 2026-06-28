import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;

public class TestLesson9 {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.mts.by/");

        System.out.println("Начало теста");
        sleep(300);

        //Клик на кнопку "Принять" (обработка файлов cookie)

        WebElement acceptButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        acceptButton.click();
        sleep(300);

        //Прокрутка до нужного блока

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1500)");
        sleep(300);

        // Проверка названия указанного блока "Онлайн пополнение без комиссии"

        String heading = driver.findElement(By.xpath("//div[@class='pay__wrapper']//h2")).getText();
        String actualHeading = "Онлайн пополнение\nбез комиссии";
        assert heading.equalsIgnoreCase(actualHeading) : "Ожидали " + actualHeading + ", а получили " + heading;
        System.out.println("Тест 1. Название блока корректно");

        // Проверка наличия логотипов платежных систем

        WebElement visaLogo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='Visa']"));
        System.out.println("Логотип Visa найден: " + visaLogo.isDisplayed());
        assert visaLogo.isDisplayed() : "Логотип Visa не отображается";

        WebElement verifiedVisaLogo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='Verified By Visa']"));
        System.out.println("Логотип Verified By Visa найден: " + verifiedVisaLogo.isDisplayed());
        assert verifiedVisaLogo.isDisplayed() : "Логотип Verified By Visa не отображается";

        WebElement mastercardLogo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='MasterCard']"));
        System.out.println("Логотип MasterCard найден: " + mastercardLogo.isDisplayed());
        assert mastercardLogo.isDisplayed() : "Логотип MasterCard не отображается";

        WebElement mastercardSecureLogo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='MasterCard Secure Code']"));
        System.out.println("Логотип MasterCard Secure Code найден: " + mastercardSecureLogo.isDisplayed());
        assert mastercardSecureLogo.isDisplayed() : "Логотип MasterCard Secure Code не отображается";

        WebElement belkartLogo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='Белкарт']"));
        System.out.println("Логотип Белкарт найден: " + belkartLogo.isDisplayed());
        assert belkartLogo.isDisplayed() : "Логотип Белкарт не отображается";

        System.out.println("Тест 2. Логотипы отобржаются");

        // Проверка работы ссылки "Подробнее о сервисе"

        WebElement link  = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        link.click();
        System.out.println("Перешли на новую страницу по кнопке 'Подробнее о сервисе'");
        sleep(500);

        driver.navigate().back();
        sleep(300);
        System.out.println("Вернулись назад");
        System.out.println("Тест 3. Ссылка рабочая");

        // Заполняем поля и проверяем работу кнопки "Продолжить"

        WebElement phoneNumber = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        phoneNumber.sendKeys("297777777");
        sleep(500);
        System.out.println("Поле номер телефона заполнено");

        WebElement sum = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        sum.sendKeys("55");
        sleep(500);
        System.out.println("Поле сумма заполнено");

        WebElement mail = driver.findElement(By.xpath("//input[@id='connection-email']"));
        mail.sendKeys("ma@ma.ru");
        sleep(500);
        System.out.println("Поле почта заполнено");

        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@class='button button__default ']"));
        continueButton.click();
        System.out.println("Кнопка продолжить нажата");
        sleep(3000);
        System.out.println("Пользователь видит окно оплаты");

        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src,'bepaid.by')]"));
        String iframeStyle = iframe.getAttribute("style");
        assert iframeStyle.contains("visibility: visible") : "iframe bePaid не виден. Style: " + iframeStyle;
        System.out.println("Кнопка продолжить работает");

        System.out.println("Тест 4. Прошел проверку");
        System.out.println("Конец теста");
    }
}
