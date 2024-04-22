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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;
import pojo.User;

import java.time.Duration;

import static data.URL.*;
import static org.junit.Assert.assertEquals;

public class ProfilePageTest {
    private WebDriver driver;
    private User user;
    String accessToken;
    @Before
    @Step("Запускаем браузер и готовим рандомные данные для регистрации аккаунта")
    public void setUp() {
        // driver = BrowserFactory.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        // driver = BrowserFactory.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        user = UserRandomizer.getNewRandomUser();
    }
    @Test
    @DisplayName("Пользователь не авторизован")
    @Description("При клике на кнопку личный кабинет переход на страницу Авторизации")
    public void checkUserNoAuthClickProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.buttonProfileClick();
        LoginPage loginPage = new LoginPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Пользователь не авторизован - переход по прямой ссылке на страницу личного кабинета")
    @Description("При запросе по прямой ссылке переход на страницу Авторизации")
    public void checkUserNoAuthGetProfileURLDirectly() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        driver.get(PROFILE_URL);
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Пользователь авторизован")
    @Description("При клике на кнопку личный кабинет переход на страницу профиля")
    public void checkUserWithAuthClickProfileButton() {
        UserMethods.createUser(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        mainPage.buttonProfileClick();
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(PROFILE_URL));
        assertEquals(PROFILE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница профиля. Клик по лого - переход на главную")
    @Description("При клике по логотипу переход на страницу профиля")
    public void checkClickLogo() {
        UserMethods.createUser(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        mainPage.buttonProfileClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogo();
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(BASE_URL));
        assertEquals(BASE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница профиля. Клик по лого - переход на главную")
    @Description("При клике по логотипу переход на страницу профиля")
    public void checkClickConstructorButton() {
        UserMethods.createUser(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        mainPage.buttonProfileClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(BASE_URL));
        assertEquals(BASE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница профиля. Разлогинивание.")
    @Description("При клике по кнопке Выход - разлогинивание и переход на страницу Логина.")
    public void checkClickExitButton() {
        UserMethods.createUser(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        mainPage.buttonProfileClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickExitButton();
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());

    }
    @After
    @Step("Удаляем профиль пользователя и закрываем браузер")
    public void tearDown() {
        Response responseLoginUser = UserMethods.loginUser(user);
        try {
            accessToken = responseLoginUser.then().log().all().extract().path("accessToken").toString();
        }
        catch (Exception e) {
            accessToken = null;
        }
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
        driver.quit();
    }
}
