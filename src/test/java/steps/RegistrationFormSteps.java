package steps;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.github.javafaker.Faker;

import org.openqa.selenium.Keys;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.qameta.allure.Step;
import pages.RegistrationPage;

public class RegistrationFormSteps {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String emailAddress = faker.internet().emailAddress();
    String gender = "Male";
    String mobile = faker.phoneNumber().subscriberNumber(10);
    Date date = faker.date().birthday(18, 55);
    String dateOfBirth = new SimpleDateFormat("d MMM yyyy").format(date);
    String subject = "Arts";
    String hobby = "Reading";
    String picture = "test-img.png";
    String address = faker.address().fullAddress();
    String state = "Uttar Pradesh";
    String city = "Merrut";
    File cv = new File(String.format("src/test/resources/%s", picture));

    RegistrationPage registrationPage = new RegistrationPage();

    @Step("Open registration page")
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill registration form")
    public void fillRegistrationForm() {
        registrationPage.firstName.setValue(firstName);
        registrationPage.lastName.setValue(lastName);
        registrationPage.email.setValue(emailAddress);
        $(byText(gender)).click();
        registrationPage.mobile.setValue(mobile);
        registrationPage.dateOfBirth.sendKeys(Keys.CONTROL + "a");
        registrationPage.dateOfBirth.sendKeys(dateOfBirth + Keys.ENTER);
        registrationPage.subjectContainer.click();
        registrationPage.subjectInput.setValue(subject).pressEnter();
        registrationPage.hobbyReadingCheckbox.click();
        registrationPage.address.setValue(address);
        registrationPage.state.scrollIntoView(true).click();
        registrationPage.findElementByText(state).click();
        registrationPage.city.click();
        registrationPage.findElementByText(city).click();
        registrationPage.chooseFileButton.uploadFile(cv);
    }

    @Step("Submit registration form")
    public void submitForm() {
        registrationPage.submitButton.click();
    }

    @Step("Verify submitted data")
    public void verifySubmittedData() {
        registrationPage.modalTitle.shouldHave(text("Thanks for submitting the form"));
        registrationPage.modalField.filterBy(text("Student name")).shouldHave(texts(firstName + " " + lastName));
        registrationPage.modalField.filterBy(text("Student email")).shouldHave(texts(emailAddress));
        registrationPage.modalField.filterBy(text("Gender")).shouldHave(texts(gender));
        registrationPage.modalField.filterBy(text("Mobile")).shouldHave(texts(mobile));
        registrationPage.modalField.filterBy(text("Date of birth")).shouldHave(texts(new SimpleDateFormat("dd MMMM,"
                + "yyyy").format(date)));
        registrationPage.modalField.filterBy(text("Subjects")).shouldHave(texts(subject));
        registrationPage.modalField.filterBy(text("Hobbies")).shouldHave(texts(hobby));
        registrationPage.modalField.filterBy(text("Picture")).shouldHave(texts(picture));
        registrationPage.modalField.filterBy(text("Address")).shouldHave(texts(address));
        registrationPage.modalField.filterBy(text("State and City")).shouldHave(texts(state + " " + city));
        registrationPage.closeModalButton.click();
    }
}
