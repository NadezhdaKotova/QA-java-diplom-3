package data;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class URL {
        public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
        public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
        public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
        public static final String USER_REGISTER = "api/auth/register";
        public static final String USER = "api/auth/user";
        public static final String USER_AUTH = "/api/auth/login";
        public static final String WRONG_PASSWORD_MESSAGE = "Некорректный пароль";
        public static final String YANDEX = "yandex";
        public static final String CHROME = "chrome";
        public static RequestSpecification spec() {
                return given()
                        .contentType(ContentType.JSON)
                        .baseUri(BASE_URL)
                        .log()
                        .all();

        }
    }
