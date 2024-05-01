package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojo.User;
import static data.URL.*;

public class RegistrationPage {
    private static final By REGISTER_NAME = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private static final By REGISTER_EMAIL = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By REGISTER_PASSWORD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    public static By REGISTER_WRONG_PASSWORD_MESSAGE = By.xpath(".//p[text()='Некорректный пароль']");
    private static final By ENTER_BUTTON = By.xpath(".//*[text()='Войти']");

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открываем страницу - Регистрация")
    public RegistrationPage openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
        return this;
    }
    @Step("Вводим имя, e-mail, пароль Пользователя - заполняем форму Регистрация")
    public RegistrationPage enterUserDataForRegistration(User user) {
        enterRegisterName(user.getName());
        enterRegisterEmail(user.getEmail());
        enterRegisterPassword(user.getPassword());
        return this;
    }
    @Step("Проверка ошибки валидации поля пароль")
    public String passwordMistakeGetText() {
        driver.findElement(REGISTER_EMAIL).click();
        return driver.findElement(REGISTER_WRONG_PASSWORD_MESSAGE).getText();
    }

    // не используются
    @Step("Вводим имя пользователя на странице Регистрация")
    public void enterRegisterName(String name) {
        driver.findElement(REGISTER_NAME).click();
        driver.findElement(REGISTER_NAME).sendKeys(name);
    }

    @Step("Вводим e-mail на странице - Регистрация")
    public void enterRegisterEmail(String email) {
        driver.findElement(REGISTER_EMAIL).click();
        driver.findElement(REGISTER_EMAIL).sendKeys(email);
    }

    @Step("Вводим пароль на странице - Регистрация")
    public void enterRegisterPassword(String password) {
        driver.findElement(REGISTER_PASSWORD).click();
        driver.findElement(REGISTER_PASSWORD).sendKeys(password);
    }


    @Step("Нажимаем кнопку - Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }
    @Step("Нажимаем кнопку - Войти")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }
}
