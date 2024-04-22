package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.URL.LOGIN_PAGE_URL;

public class ProfilePage {
    private static final By profileNameField = By.xpath(".//input[@name='Name']");
    private static final By burgerLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private static final By exitButton = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверяем - отображается ли имя профиля?")
    public boolean isProfileNameFieldDisplayed() {
        return driver.findElement(profileNameField).isDisplayed();
    }

    @Step("Нажимаем кнопку - Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажимаем на кнопку логотипа в шапке сайта")
    public void clickLogo() {
        driver.findElement(burgerLogo).click();
    }

    @Step("Нажимаем на кнопку - Выход - в личном кабинете")
    public void clickExitButton() {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }
}
