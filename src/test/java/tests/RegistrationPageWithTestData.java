package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static tests.TestData.*;

public class RegistrationPageWithTestData extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void minimalSuccessfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserMobileNumber(userPhoneNumber)
                .setGender(userGender)
                .setUserDateOfBirth(usersDayOfBirth, usersMonthOfBirth, usersYearOfBirth)

                .submit()

                .checkUserSubmitResults("Student Name", firstName + " " + lastName)
                .checkUserSubmitResults("Student Email", userEmail)
                .checkUserSubmitResults("Gender", userGender)
                .checkUserSubmitResults("Mobile", userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", usersDayOfBirth + " " + usersMonthOfBirth + "," + usersYearOfBirth);

    }

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserMobileNumber(userPhoneNumber)
                .setGender(userGender)
                .setUserDateOfBirth(usersDayOfBirth, usersMonthOfBirth, usersYearOfBirth)
                .setUserSubjects(usersSubject)
                .setUserHobbies(usersHobbie)
                .uploadPicture(usersPicture)
                .setUserAddress(userAddress)
                .setState(usersState)
                .setCity(usersCity)

                .submit()

                .checkUserSubmitResults("Student Name", firstName + " " + lastName)
                .checkUserSubmitResults("Student Email", userEmail)
                .checkUserSubmitResults("Gender", userGender)
                .checkUserSubmitResults("Mobile", userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", usersDayOfBirth + " " + usersMonthOfBirth + "," + usersYearOfBirth)
                .checkUserSubmitResults("Subjects", usersSubject)
                .checkUserSubmitResults("Hobbies", usersHobbie)
                .checkUserSubmitResults("Picture", usersPicture)
                .checkUserSubmitResults("Address", userAddress)
                .checkUserSubmitResults("State and City", usersState + " " + usersCity);

    }

    @Test
    void unsuccessfulRegistrationTest() {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();

    }
}
