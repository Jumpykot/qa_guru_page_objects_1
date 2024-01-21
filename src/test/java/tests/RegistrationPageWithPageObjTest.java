package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationPageWithPageObjTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Biba")
                .setLastName("Bubov")
                .setUserEmail("Buba@biba.com")
                .setUserMobileNumber("7123456789")
                .setGender("Male")
                .setUserDateOfBirth("16", "February", "1995")
                .setUserSubjects("Arts")
                .setUserHobbies("Sports")
                .uploadPicture("apple-touch-icon.png")
                .setUserAddress("Baker street 221b")
                .setState("NCR")
                .setCity("Delhi")

                .submit();


                sleep(5000);

    }
}
