package data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;

/**
 * Паттерн Фабрика:
 * реализуем выбор браузера yandex/chrome
 */
public class BrowserFactory {
    public static WebDriver getDriver() {

        WebDriver driver;
        String browserName = System.getProperty("browser");
        if (browserName==null) { browserName = ""; };

        ChromeOptions options = new ChromeOptions();
        switch (browserName) {
            case "chrome":
            case "":
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver = new Augmenter().augment(driver);
                break;
            case "yandex":
                driver = new ChromeDriver(options);
                driver = new Augmenter().augment(driver);
                break;
            default:throw new RuntimeException("Incorrect BrowserName: "+browserName);
        }

        return driver;
    }
}