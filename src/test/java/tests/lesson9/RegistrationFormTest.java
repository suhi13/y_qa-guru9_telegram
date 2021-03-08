package tests.lesson9;

import static com.codeborne.selenide.Condition.visible;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.RegistrationPage;
import steps.RegistrationFormSteps;

public class RegistrationFormTest extends TestBase {

    RegistrationFormSteps steps = new RegistrationFormSteps();

    @Test
    @Owner("Yuliia Sukhova")
    @Tag("positive")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can register")
    @Feature("Registration")
    @DisplayName("User submits filled registration form")
    public void checkRegistrationFormTest() {
        steps.openPage();
        steps.fillRegistrationForm();
        steps.submitForm();
        steps.verifySubmittedData();
    }

    @Test
    @Owner("Yuliia Sukhova")
    @Tag("negative")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can register")
    @Feature("Registration")
    @DisplayName("User submits empty registration form")
    void checkSubmittingEmptyRegistrationFormTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        steps.openPage();
        steps.submitForm();
        registrationPage.modalTitle.shouldBe(visible);
    }
}