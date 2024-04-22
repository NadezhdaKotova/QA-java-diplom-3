package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.URL.*;

public class MainPage {
    private static final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By orderButton = By.xpath(".//div[@class='BurgerConstructor_basket__container__2fUl3 mt-10']/button");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открываем страницу - Главная")
    public void openMainPage() {
        driver.get(BASE_URL);
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(BASE_URL));
    }
    @Step("Извлечение текста на кнопке Оформить заказ")
    public String buttonCreateOrderGetText() {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return driver.findElement(orderButton).getText();
    }
    @Step("Извлечение текста на кнопке Оформить заказ")
    public void buttonCreateOrderClick() {
        driver.findElement(orderButton).click();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void buttonProfileClick() {
        driver.findElement(profileButton).click();
    }
}
