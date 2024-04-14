import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class CountersTest {

    @BeforeEach
    void setup() {  // нейтрализация проверки паролей при обновлении Google Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;

        //loginPage = open("https://www.avito.ru/avito-care/eco-impact", LoginPage.class);
        open("https://www.avito.ru/avito-care/eco-impact");
    }

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldTestUnauthorizedUser() {
        $(By.className("desktop-value-Nd1tR")).shouldHave(Condition.text("0"));
    }

    @Test
    void shouldTestAuthorizedUser() {
        $(By.className("desktop-button-wrapper-K8ki0")).shouldHave(Condition.text("Авторизоваться")).click();
        sleep(2000);
        SelenideElement authForm = $(By.className("AuthForm-login-U0tK7"));
    }
}
