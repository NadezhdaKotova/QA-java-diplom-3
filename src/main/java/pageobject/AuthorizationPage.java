package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static data.URL.*;

public class AuthorizationPage {
    private final WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }



}
