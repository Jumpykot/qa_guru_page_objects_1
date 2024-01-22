package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxTestPage;

import static com.codeborne.selenide.Selenide.sleep;

public class TextBoxTestWithPageObj extends TestBase {

    TextBoxTestPage textBoxTestPage = new TextBoxTestPage();

    @Test
    void successfulTextBoxTest() {
        textBoxTestPage.openPage()
                .setFullName("Biba Bubov")
                .setEmail("BibaBubov@chpoks.ru")
                .setCurrentAddress("Street Palm 34")
                .setPermanentAddress("Street jizza pizza 45")

                .submit()

                .checkResults("name", "Biba Bubov")
                .checkResults("email", "BibaBubov@chpoks.ru")
                .checkResults("currentAddress", "Street Palm 34")
                .checkResults("permanentAddress ", "Street jizza pizza 45");

    }
}
