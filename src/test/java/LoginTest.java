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

public class LoginTest {
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
    @DisplayName("Главная. Пользователь не авторизован - проверка текста кнопки Войти в аккаунт")
    @Description("Если пользователь не авторизован, на кнопке Оформить заказ текст - Войти в аккаунт")
    public void checkTextOnButtonCreateOrderUserNotAuth() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Войти в аккаунт",buttonText);
    }
    @Test
    @DisplayName("Главная. Пользователь авторизован - проверка текста кнопки Оформить заказ")
    @Description("Если пользователь авторизован, на кнопке Оформить заказ текст Оформить заказ")
    public void checkTextOnButtonCreateOrderUserAuth() {
        UserMethods.createUser(user);
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.buttonCreateOrderClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Успешная авторизация пользователя через кнопку Войти в аккаунт")
    @Description("Главная - кнопка Войти в аккаунт - страница авторизации, успешная авторизация - редирект на главную, на кнопке уже оформить заказ")
    public void checkSuccessLoginFromMainPageButtonEnterAccount() {
        UserMethods.createUser(user);
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.buttonCreateOrderClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Успешная авторизация пользователя через кнопку Личный кабинет")
    @Description("Главная - Личный кабинет - страница авторизации, успешная авторизация - редирект на главную, на кнопке уже оформить заказ")
    public void checkSuccessLoginFromMainPageButtonProfile() {
        UserMethods.createUser(user);
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.buttonProfileClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Успешная авторизация пользователя через кнопку Войти в форме регистрации")
    @Description("Страница регистрации - Вход - страница авторизации, успешная авторизация - редирект на главную, на кнопке уже оформить заказ")
    public void checkSuccessLoginFromRegistrationPageButtonEnter() {
        UserMethods.createUser(user);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                        .clickEnterButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Успешная авторизация пользователя через кнопку в форме восстановления пароля")
    @Description("Страница регистрации - Вход - страница авторизации, успешная авторизация - редирект на главную, на кнопке уже оформить заказ")
    public void checkSuccessLoginFromForgotPasswordPageButtonEnter() {
        UserMethods.createUser(user);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage().clickEnterButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Страница Вход. Успешная авторизация. Все поля корректно.")
    @Description("Кнопка Войти - После авторизации пользователь попадает на главную страницу.")
    public void checkSuccessLoginWithCorrectEmailAndCorrectPassword() {
        UserMethods.createUser(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        MainPage mainPage = new MainPage(driver);
        String buttonText = mainPage.buttonCreateOrderGetText();
        assertEquals("Текст на кнопке неверный","Оформить заказ",buttonText);
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Все поля некорректно.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithIncorrectEmailAndIncorrectPassword() {
        UserMethods.createUser(user);
        String newEmail = "yandex" + user.getEmail();
        String newPassword = "yandex" + user.getPassword();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(newEmail, newPassword);
        loginPage.clickEnterButton();
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Email корректно. Пароль некорректно.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithСorrectEmailAndIncorrectPassword() {
        UserMethods.createUser(user);
        String newPassword = "yandex" + user.getPassword();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), newPassword);
        loginPage.clickEnterButton();
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Email некорректно. Пароль корректно.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithIncorrectEmailAndCorrectPassword() {
        UserMethods.createUser(user);
        String newEmail = "yandex" + user.getEmail();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(newEmail, user.getPassword());
        loginPage.clickEnterButton();
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Все поля пусто.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithNoEmailAndNoPassword() {
        UserMethods.createUser(user);
        String newEmail = "";
        String newPassword = "";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(newEmail, newPassword);
        loginPage.clickEnterButton();
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Email корректно, пароль пусто.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithCorrectEmailAndNoPassword() {
        UserMethods.createUser(user);
        String newPassword = "";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(user.getEmail(), newPassword);
        loginPage.clickEnterButton();
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Страница Вход. Неуспешная авторизация. Email пусто, пароль корректно.")
    @Description("Кнопка Войти - авторизация неуспешна. Пользователь остается на той же странице.")
    public void checkLoginWithNoEmailAndCorrectPassword() {
        UserMethods.createUser(user);
        String newEmail = "";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage().authorization(newEmail, user.getPassword());
        loginPage.clickEnterButton();
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
