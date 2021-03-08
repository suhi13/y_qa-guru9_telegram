package tests.lesson9;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelpers.*;

public class TestBase {
    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = true;
        System.setProperty("remote_driver", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        if(System.getProperty("remote_driver") != null) {
            // config for Java + Selenide
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = System.getProperty("remote_driver");
        }
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if(System.getProperty("video_storage") != null)
            attachVideo();
        closeWebDriver();
    }
}