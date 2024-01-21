package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userDateOfBirth = $("#dateOfBirthInput"),
            userSubjects = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            fileDownload = $("#uploadPicture"),
            userAddress = $("#currentAddress"),
            userState = $("#state"),
            userCity = $("#city");

    CalendarComponent calendarComponent = new CalendarComponent();


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }
    public RegistrationPage setLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender (String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserMobileNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserDateOfBirth(String day, String month, String year) {
        userDateOfBirth.click();

        calendarComponent.setDate(day, month, year);

        return this;

    }

    public RegistrationPage setUserSubjects (String value) {
        userSubjects.setValue(value).pressTab();

        return this;
    }

    public RegistrationPage setUserHobbies (String value) {
        userHobbies.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture (String value) {
        fileDownload.uploadFromClasspath(value);

        return this;
    }
    public RegistrationPage setUserAddress (String value) {
        userAddress.setValue(value);

        return this;
    }

    public RegistrationPage setState (String value) {
        userState.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity (String value) {
        userCity.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationPage submit() {
        $("#submit").click();

        return this;
    }

}
