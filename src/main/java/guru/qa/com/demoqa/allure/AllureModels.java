package guru.qa.com.demoqa.allure;


import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

public class AllureModels {

    public void attachments(String screenshotName, String pageName) {

        Allure.step("Приложения", () -> {

            Allure.getLifecycle().addAttachment(
                    screenshotName,
                    "image/png",
                    "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));

            Allure.getLifecycle().addAttachment(
                    pageName,
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));

        });

    }

    public void attachment(AttachmentType attachmentType, String attachmentTitle) {

        if (attachmentType == AttachmentType.PAGE) {

            Allure.step("Приложение", () ->

                    io.qameta.allure.Allure.getLifecycle().addAttachment(
                            attachmentTitle,
                            "text/html",
                            "html",
                            WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8))

            );

        } else if (attachmentType == AttachmentType.SCREENSHOT) {

            Allure.step("Приложение", () ->

                    io.qameta.allure.Allure.getLifecycle().addAttachment(
                            attachmentTitle,
                            "image/png",
                            "png",
                            ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES))

            );

        }

    }

}
