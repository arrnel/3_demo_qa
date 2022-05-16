package guru.qa.com.demoqa.allure;


import com.codeborne.selenide.Selenide;
import guru.qa.com.demoqa.setup.TestBase;
import guru.qa.com.demoqa.setup.TestEnvironment;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class AllureModels {

    TestEnvironment testEnv = new TestBase().getTestEnvironment();

    public void attachment(AttachmentType attachmentType, String attachmentTitle) {

        if (attachmentType == AttachmentType.PAGE) {

            Allure.step("Страница", () ->

                    Allure.getLifecycle().addAttachment(
                            attachmentTitle,
                            "text/html",
                            "html",
                            getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8))

            );

        } else if (attachmentType == AttachmentType.SCREENSHOT) {

            Allure.step("Скриншот страницы", () ->

                    Allure.getLifecycle().addAttachment(
                            attachmentTitle,
                            "image/png",
                            "png",
                            ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES))

            );

        } else if (attachmentType == AttachmentType.VIDEO) {

            Allure.step("Видео", () -> {

                String videoCode = "";

                String htmlVideoCode = getHTMLVideoCode();

                Allure.getLifecycle().addAttachment(
                        attachmentTitle,
                        "text/html",
                        ".html",
                        htmlVideoCode.getBytes(StandardCharsets.UTF_8));
            });

        } else if (attachmentType == AttachmentType.LOGS) {

            Allure.step("Логи браузера", () -> {

                String browserLogs = String.join("\n", Selenide.getWebDriverLogs(BROWSER));

                Allure.getLifecycle().addAttachment(
                        attachmentTitle,
                        "text/plain",
                        ".html",
                        browserLogs.getBytes(StandardCharsets.UTF_8));
            });

        }

    }

    @NotNull
    private String getHTMLVideoCode() {

        String videoCode;

        if (testEnv != TestEnvironment.LOCAL){
            videoCode = "<video width='100%' height='100%' controls autoplay>" +
                    "<source src='" + getVideoUrl(getSessionId()) + "' type='video/mp4'></video>";
        } else {
            videoCode = "<h1>Видео не доступно.</h1>";
        }

        return "<html><body>" + videoCode + "</body></html>";

    }

    String getSessionId() {
        if (testEnv == TestEnvironment.REMOTE) {
            return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        } else {
            return sessionId().toString();
        }
    }

    @NotNull
    URL getVideoUrl(String sessionId) {
        String videoUrl;
        if (testEnv == TestEnvironment.REMOTE) {
            videoUrl = String.format("https://%s/video/%s.mp4", System.getenv("hostRemote"), sessionId);
        } else {
            // Пока локально не сохраняет селеноид видео
            videoUrl = "";
        }

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void allAttachments(String pageTitle, String screenshotTitle, String videoTitle, String logsTitle) {

        Allure.step("Приложения", () -> {
            attachment(AttachmentType.PAGE, pageTitle);
            attachment(AttachmentType.SCREENSHOT, screenshotTitle);
            attachment(AttachmentType.VIDEO, videoTitle);
            attachment(AttachmentType.LOGS, logsTitle);
        });

    }

}
