package data;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.User;
import static data.URL.*;
public class UserMethods {
    @Step("API Авторизация пользователя")
    public static Response loginUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(USER_AUTH);
    }

    @Step("API Удаление пользователя")
    public static void deleteUser(String accessToken) {
        spec()
                .header("Authorization",accessToken)
                .when()
                .delete(USER);
    }
    @Step("API Создание нового пользователя")
    public static Response createUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(USER_REGISTER);
    }
}