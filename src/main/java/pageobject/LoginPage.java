package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static data.URL.*;

public class LoginPage {
    private static final By loginIndicator = By.xpath(".//*[text()='Вход']");
    private static final By loginEnterButton = By.xpath(".//*[text()='Войти']");
    private static final By loginEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By loginPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By registerButtonFromLogin = By.xpath(".//*[text()='Зарегистрироваться']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открываем страницу - Вход")
    public LoginPage openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        return this;
    }
    @Step("Заполняем поля формы авторизации - e-mail  и пароль Пользователя")
    public void authorization(String email, String password) {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        driver.findElement(loginEmail).click();
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).click();
        driver.findElement(loginPassword).sendKeys(password);
    }
    @Step("Нажимаем кнопку - Войти - на странице Вход (авторизация по e-mail и паролю)")
    public void clickEnterButton() {
        driver.findElement(loginEnterButton).click();
    }



}
