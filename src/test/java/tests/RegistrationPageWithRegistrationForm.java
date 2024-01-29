package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testData.RegistrationForm;

public class RegistrationPageWithRegistrationForm extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationForm registrationForm = new RegistrationForm();

    @Test
    void minimalSuccessfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(registrationForm.firstName)
                .setLastName(registrationForm.lastName)
                .setUserEmail(registrationForm.userEmail)
                .setUserMobileNumber(registrationForm.userPhoneNumber)
                .setGender(registrationForm.userGender)
                .setUserDateOfBirth(registrationForm.usersDayOfBirth, registrationForm.usersMonthOfBirth, registrationForm.usersYearOfBirth)

                .submit()

                .checkUserSubmitResults("Student Name", registrationForm.firstName + " " + registrationForm.lastName)
                .checkUserSubmitResults("Student Email", registrationForm.userEmail)
                .checkUserSubmitResults("Gender", registrationForm.userGender)
                .checkUserSubmitResults("Mobile", registrationForm.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", registrationForm.usersDayOfBirth + " " + registrationForm.usersMonthOfBirth + "," + registrationForm.usersYearOfBirth);

    }

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(registrationForm.firstName)
                .setLastName(registrationForm.lastName)
                .setUserEmail(registrationForm.userEmail)
                .setUserMobileNumber(registrationForm.userPhoneNumber)
                .setGender(registrationForm.userGender)
                .setUserDateOfBirth(registrationForm.usersDayOfBirth, registrationForm.usersMonthOfBirth, registrationForm.usersYearOfBirth)
                .setUserSubjects(registrationForm.usersSubject)
                .setUserHobbies(registrationForm.usersHobbie)
                .uploadPicture(registrationForm.usersPicture)
                .setUserAddress(registrationForm.userAddress)
                .setState(registrationForm.usersState)
                .setCity(registrationForm.usersCity)

                .submit()

                .checkUserSubmitResults("Student Name", registrationForm.firstName + " " + registrationForm.lastName)
                .checkUserSubmitResults("Student Email", registrationForm.userEmail)
                .checkUserSubmitResults("Gender", registrationForm.userGender)
                .checkUserSubmitResults("Mobile", registrationForm.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", registrationForm.usersDayOfBirth + " " + registrationForm.usersMonthOfBirth + "," + registrationForm.usersYearOfBirth)
                .checkUserSubmitResults("Subjects", registrationForm.usersSubject)
                .checkUserSubmitResults("Hobbies", registrationForm.usersHobbie)
                .checkUserSubmitResults("Picture", registrationForm.usersPicture)
                .checkUserSubmitResults("Address", registrationForm.userAddress)
                .checkUserSubmitResults("State and City", registrationForm.usersState + " " + registrationForm.usersCity);

    }

    @Test
    void unsuccessfulRegistrationTest() {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();

    }
}
