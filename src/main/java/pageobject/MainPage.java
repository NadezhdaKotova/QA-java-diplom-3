package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.URL.*;

public class MainPage {
    private static final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By orderButton = By.xpath(".//div[@class='BurgerConstructor_basket__container__2fUl3 mt-10']/button");
    private static final By menuBunButton = By.xpath(".//span[text()='Булки']");
    private static final By menuFillingsButton = By.xpath(".//span[text()='Начинки']");
    private static final By menuSauceButton = By.xpath(".//span[text()='Соусы']");
    private static final By menuInFocus = By.cssSelector("div.tab_tab__1SPyG.tab_tab_type_current__2BEPc.pt-4.pr-10.pb-4.pl-10");


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
    @Step("Нажать на закладку - Булки")
    public void clickMenuBun() {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.elementToBeClickable(menuBunButton));
        driver.findElement(menuBunButton).click();
    }

    @Step("Нажать на закладку - Соусы")
    public void clickMenuSauce() {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.elementToBeClickable(menuSauceButton));
        driver.findElement(menuSauceButton).click();
    }

    @Step("Нажать на закладку - Начинки")
    public void clickMenuFillings() {
        driver.findElement(menuFillingsButton).click();
    }

    @Step("Проверяем текст текущего меню")
    public String getTextFromSelectedMenu() {
        return driver.findElement(menuInFocus).getText();
    }
}
