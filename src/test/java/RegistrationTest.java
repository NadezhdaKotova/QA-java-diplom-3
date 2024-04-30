import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.RegistrationPage;
import java.time.Duration;
import static data.URL.*;
import static org.junit.Assert.assertEquals;

public class RegistrationTest extends BeforeAndAfter{
    @Test
    @DisplayName("Успешная регистрация пользователя. Пароль 6 символов")
    @Description("Страница Регистрация - поля имя корректно, email корректно, пароль 6 символов. Проверка: пользователь попадает на страницу логина")
    public void successRegistrationWithAllCorrectFields() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user)
                .clickRegistrationButton();
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals(LOGIN_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Регистрация пользователя с паролем в 5 символов")
    @Description("Страница Регистрация - поля имя корректно, email корректно, пароль 5 символов. Проверка: при снятии фокуса с поля пароль появляется сообщение об ошибке валидации")
    public void registrationWithPasswordFiveSymbolsMistakeMessage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        //изменяем пароль, регистрируемся с этими данными
        user.setPassword("12345");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user);
        //запоминаем текст ошибки
        String mistake = registrationPage.passwordMistakeGetText();
        //проверка
        assertEquals("Текст ошибки поля Пароль не соответствует требованиям",WRONG_PASSWORD_MESSAGE,mistake);
    }
    @Test
    @DisplayName("Регистрация пользователя с паролем в 5 символов - клик по кнопке НЕ приводит к переходу на страницу логина")
    @Description("Страница Регистрация - поля имя корректно, email корректно, пароль 5 символов. Проверка: при нажатии на кнопку регистрация не происходит. Пользователь остается на странице регистрации.")
    public void registrationWithPasswordFiveSymbols() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        user.setPassword("12345");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user)
                .clickRegistrationButton();
        assertEquals(REGISTER_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Регистрация пользователя с пустыми полями - клик по кнопке НЕ приводит к переходу на страницу логина")
    @Description("Страница Регистрация - поля имя пусто, email пусто, пароль пусто. Проверка: при нажатии на кнопку регистрация не происходит. Пользователь остается на странице регистрации.")
    public void registrationWithNoAnyData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        //изменяем значения на пусто, регистрируемся с этими данными
        user.setName("");
        user.setPassword("");
        user.setEmail("");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user);
        //проверка, что перехода на страницу логина не произошло
        assertEquals(REGISTER_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Поле Имя пусто - неуспешная регистрация")
    @Description("Страница Регистрация - поля имя пусто, email корректно, пароль корректно. Проверка: при нажатии на кнопку регистрация не происходит. Пользователь остается на странице регистрации.")
    public void registrationWithNoNameData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        //изменяем значения на пусто, регистрируемся с этими данными
        user.setName("");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user);
        //проверка, что перехода на страницу логина не произошло
        assertEquals(REGISTER_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Поле Email пусто - неуспешная регистрация")
    @Description("Страница Регистрация - поля имя корректно, email пусто, пароль корректно. Проверка: при нажатии на кнопку регистрация не происходит. Пользователь остается на странице регистрации.")
    public void registrationWithNoEmailData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        //изменяем значения на пусто, регистрируемся с этими данными
        user.setEmail("");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user);
        //проверка, что перехода на страницу логина не произошло
        assertEquals(REGISTER_PAGE_URL,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Поле Пароль пусто - неуспешная регистрация")
    @Description("Страница Регистрация - поля имя корректно, email корректно, пароль пусто. Проверка: при нажатии на кнопку регистрация не происходит. Пользователь остается на странице регистрации.")
    public void registrationWithNoPasswordData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        //изменяем значения на пусто, регистрируемся с этими данными
        user.setPassword("");
        registrationPage.openRegisterPage()
                .enterUserDataForRegistration(user);
        //проверка, что перехода на страницу логина не произошло
        assertEquals(REGISTER_PAGE_URL,driver.getCurrentUrl());
    }
}
