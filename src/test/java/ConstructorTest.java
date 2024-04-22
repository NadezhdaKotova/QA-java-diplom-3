import data.UserMethods;
import data.UserRandomizer;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.ForgotPasswordPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import pojo.User;

import static data.URL.LOGIN_PAGE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    private WebDriver driver;
    private User user;
    String accessToken;
    @Before
    @Step("Запускаем браузер")
    public void setUp() {
        // driver = BrowserFactory.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        // driver = BrowserFactory.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Клик по кнопке Булки открывает булки")
    @Description("")
    public void clickBuns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickMenuSauce();
        mainPage.clickMenuBun();
        assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Клик по кнопке Булки открывает булки")
    @Description("")
    public void clickSauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickMenuSauce();
        assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Клик по кнопке Начинки открывает Начинки")
    @Description("")
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
