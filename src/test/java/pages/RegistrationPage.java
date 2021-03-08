package pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class RegistrationPage {

    public SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            mobile = $("#userNumber"),
            dateOfBirth = $("#dateOfBirthInput"),
            subjectContainer = $("#subjectsContainer"),
            subjectInput = $("#subjectsInput"),
            hobbyReadingCheckbox = $("#hobbies-checkbox-2").parent(),
            address = $("#currentAddress"),
            state = $(byText("Select State")),
            city = $(byText("Select City")),
            chooseFileButton = $("#uploadPicture"),
            submitButton = $("#submit"),
            modalTitle = $(".modal-title"),
            closeModalButton = $("#closeLargeModal");

    public ElementsCollection modalField = $$(".table-responsive tr");

    public SelenideElement findElementByText(String text) {
        return $(byText(text));
    }
}
