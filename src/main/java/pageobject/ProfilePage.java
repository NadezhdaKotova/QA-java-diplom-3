package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private static final By PROFILE_NAME_FIELD = By.xpath(".//input[@name='Name']");
    private static final By BURGER_LOGO = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверяем - отображается ли имя профиля?")
    public boolean isProfileNameFieldDisplayed() {
        return driver.findElement(PROFILE_NAME_FIELD).isDisplayed();
    }

    @Step("Нажимаем кнопку - Конструктор")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Нажимаем на кнопку логотипа в шапке сайта")
    public void clickLogo() {
        driver.findElement(BURGER_LOGO).click();
    }

    @Step("Нажимаем на кнопку - Выход - в личном кабинете")
    public void clickExitButton() {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.elementToBeClickable(EXIT_BUTTON));
        driver.findElement(EXIT_BUTTON).click();
    }
}
