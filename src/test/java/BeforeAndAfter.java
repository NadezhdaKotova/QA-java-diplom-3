import data.BrowserFactory;
import data.UserMethods;
import data.UserRandomizer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pojo.User;

public class BeforeAndAfter {
    User user;
    String accessToken;

    WebDriver driver;
    @Before
    @Step("Запускаем браузер и готовим рандомные данные для регистрации аккаунта")
    public void setUp() {
        driver = BrowserFactory.getDriver();
        user = UserRandomizer.getNewRandomUser();
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
