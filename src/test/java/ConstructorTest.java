import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BeforeAndAfter {

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
}
