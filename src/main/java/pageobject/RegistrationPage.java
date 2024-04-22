package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojo.User;
import static data.URL.*;

public class RegistrationPage {
    private static final By registerName = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private static final By registerEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By registerPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public static By registerWrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
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
        driver.findElement(registerEmail).click();
        return driver.findElement(registerWrongPasswordMessage).getText();
    }

    // не используются
    @Step("Вводим имя пользователя на странице Регистрация")
    public void enterRegisterName(String name) {
        driver.findElement(registerName).click();
        driver.findElement(registerName).sendKeys(name);
    }

    @Step("Вводим e-mail на странице - Регистрация")
    public void enterRegisterEmail(String email) {
        driver.findElement(registerEmail).click();
        driver.findElement(registerEmail).sendKeys(email);
    }

    @Step("Вводим пароль на странице - Регистрация")
    public void enterRegisterPassword(String password) {
        driver.findElement(registerPassword).click();
        driver.findElement(registerPassword).sendKeys(password);
    }


    @Step("Нажимаем кнопку - Зарегистрироваться")
    public AuthorizationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new AuthorizationPage(driver);
    }
}
