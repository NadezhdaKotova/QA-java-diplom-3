package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static data.URL.*;

public class LoginPage {
    private static final By LOGIN_INDICATOR = By.xpath(".//*[text()='Вход']");
    private static final By LOGIN_ENTER_BUTTON = By.xpath(".//*[text()='Войти']");
    private static final By LOGIN_EMAIL = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By LOGIN_PASSWORD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By REGISTER_BUTTON_FROM_LOGIN = By.xpath(".//*[text()='Зарегистрироваться']");

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
        driver.findElement(LOGIN_EMAIL).click();
        driver.findElement(LOGIN_EMAIL).sendKeys(email);
        driver.findElement(LOGIN_PASSWORD).click();
        driver.findElement(LOGIN_PASSWORD).sendKeys(password);
    }
    @Step("Нажимаем кнопку - Войти - на странице Вход (авторизация по e-mail и паролю)")
    public void clickEnterButton() {
        driver.findElement(LOGIN_ENTER_BUTTON).click();
    }



}
