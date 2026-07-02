package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final WebDriver driver;

    // Дропдаун выбора таба
    private final By dropdownButton = By.xpath("//button[@class='select__header']");

    // Услуги связи
    private final By phoneConnection = By.id("connection-phone");
    private final By sumConnection   = By.id("connection-sum");
    private final By emailConnection = By.id("connection-email");

    // Домашний интернет
    private final By phoneInternet   = By.id("internet-phone");
    private final By sumInternet     = By.id("internet-sum");
    private final By emailInternet   = By.id("internet-email");

    // Рассрочка
    private final By scoreInstalment = By.id("score-instalment");
    private final By sumInstalment   = By.id("instalment-sum");
    private final By emailInstalment = By.id("instalment-email");

    // Задолженность
    private final By scoreArrears    = By.id("score-arrears");
    private final By sumArrears      = By.id("arrears-sum");
    private final By emailArrears    = By.id("arrears-email");

    // Кнопка продолжить (Услуги связи)
    private final By continueButton  = By.xpath("//form[@id='pay-connection']//button[@class='button button__default ']");

    // Внутри iframe — сумма, телефон, кнопка оплаты
    private final By iFrameAmount    = By.xpath("//div[contains(@class,'pay-description__cost')]//span");
    private final By iFramePhone     = By.xpath("//div[contains(@class,'pay-description__text')]//span");
    private final By iFramePayButton = By.xpath("//button[contains(@class,'colored')]//span");

    // Поля карты (по label)
    private final By iFrameLabelCardNumber = By.xpath("//label[contains(text(),'Номер карты')]");
    private final By iFrameLabelExpiry     = By.xpath("//label[contains(text(),'Срок действия')]");
    private final By iFrameLabelCvc        = By.xpath("//label[contains(text(),'CVC')]");
    private final By iFrameLabelHolder     = By.xpath("//label[contains(text(),'Имя и фамилия на карте')]");

    // Иконки платёжных систем
    private final By iFrameIconVisa       = By.xpath("//img[contains(@src,'visa-system')]");
    private final By iFrameIconMastercard = By.xpath("//img[contains(@src,'mastercard-system')]");
    private final By iFrameIconBelkart    = By.xpath("//img[contains(@src,'belkart-system')]");
    private final By iFrameIconMaestro    = By.xpath("//img[contains(@src,'maestro-system')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Принять куки
    public void acceptCookies() {
        driver.findElement(By.id("cookie-agree")).click();
    }

    // Прокрутить до блока оплаты
    public void scrollToPayBlock() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1500)");
    }

    // Переключить таб через дропдаун
    public void selectTab(String tabName) {
        WebElement btn = driver.findElement(dropdownButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
        WebElement option = driver.findElement(
                By.xpath("//p[@class='select__option'][text()='" + tabName + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    }

    // Получить placeholder поля
    public String getPlaceholder(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }

    // Заполнить форму "Услуги связи" и нажать продолжить
    public void fillConnectionFormAndSubmit(String phone, String sum, String email) {
        WebElement phoneField = driver.findElement(phoneConnection);
        phoneField.clear();
        phoneField.sendKeys(phone);

        WebElement sumField = driver.findElement(sumConnection);
        sumField.clear();
        sumField.sendKeys(sum);

        WebElement emailField = driver.findElement(emailConnection);
        emailField.clear();
        emailField.sendKeys(email);

        driver.findElement(continueButton).click();
    }

    // Геттеры данных из iframe
    public String getIFrameAmount()         { return driver.findElement(iFrameAmount).getText(); }
    public String getIFramePhone()          { return driver.findElement(iFramePhone).getText(); }
    public String getIFramePayButtonText()  { return driver.findElement(iFramePayButton).getText(); }
    public String getIFrameLabelText(By locator) { return driver.findElement(locator).getText(); }
    public boolean isIFrameIconDisplayed(By locator) { return driver.findElement(locator).isDisplayed(); }

    // Геттеры локаторов основных полей
    public By getPhoneConnection() { return phoneConnection; }
    public By getSumConnection()   { return sumConnection; }
    public By getEmailConnection() { return emailConnection; }

    public By getPhoneInternet()   { return phoneInternet; }
    public By getSumInternet()     { return sumInternet; }
    public By getEmailInternet()   { return emailInternet; }

    public By getScoreInstalment() { return scoreInstalment; }
    public By getSumInstalment()   { return sumInstalment; }
    public By getEmailInstalment() { return emailInstalment; }

    public By getScoreArrears()    { return scoreArrears; }
    public By getSumArrears()      { return sumArrears; }
    public By getEmailArrears()    { return emailArrears; }

    // Геттеры локаторов iframe
    public By getIFrameLabelCardNumber() { return iFrameLabelCardNumber; }
    public By getIFrameLabelExpiry()     { return iFrameLabelExpiry; }
    public By getIFrameLabelCvc()        { return iFrameLabelCvc; }
    public By getIFrameLabelHolder()     { return iFrameLabelHolder; }

    public By getIFrameIconVisa()        { return iFrameIconVisa; }
    public By getIFrameIconMastercard()  { return iFrameIconMastercard; }
    public By getIFrameIconBelkart()     { return iFrameIconBelkart; }
    public By getIFrameIconMaestro()     { return iFrameIconMaestro; }
}