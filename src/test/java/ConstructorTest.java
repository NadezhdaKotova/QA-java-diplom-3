import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import static org.junit.Assert.assertEquals;
import data.BrowserFactory;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    @Step("Запускаем браузер")
    public void setUp() {
        //driver = BrowserFactory.getDriver("yandex"); // проверен запуск Яндекс Браузера
        driver = BrowserFactory.getDriver("chrome"); // проверен запуск Яндекс Браузера Chrome
    }
    @Test
    @DisplayName("Клик по кнопке Булки открывает булки")
    @Description("Проверка, что при клике на булки в зону видимости приезжает заголовок Булки")
    public void clickBuns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickMenuSauce();
        mainPage.clickMenuBun();
        assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Клик по кнопке Булки открывает соусы")
    @Description("Проверка, что при клике на булки в зону видимости приезжает заголовок Соусы")
    public void clickSauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickMenuSauce();
        assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Клик по кнопке Начинки открывает Начинки")
    @Description("Проверка, что при клике на булки в зону видимости приезжает заголовок Начинки")
    public void clickFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickMenuFillings();
        assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }
    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        driver.quit();
    }
}
