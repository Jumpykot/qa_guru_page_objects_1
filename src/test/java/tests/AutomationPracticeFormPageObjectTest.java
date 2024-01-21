package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormPageObjectTest {

    @Test
    void fillAutomationPracticeForm() {

        //simpleFields
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("TestName");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("Test@test.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //dateOfBirth


        //Subjects
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();

        //Hobbies
        $(byText("Sports")).ancestor(".custom-checkbox").click();
        $(byText("Reading")).ancestor(".custom-checkbox").click();
        $(byText("Music")).ancestor(".custom-checkbox").click();

        //Picture
        $("#uploadPicture").uploadFromClasspath("вторичка.jpeg");

        //Current Address
        $("#currentAddress").setValue("TestAddress");

        //State and city
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        //Submit
        $("#submit").click();

        //Result
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("TestName TestLastName"));
        $(".table-responsive").shouldHave(text("Test@test.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("16 February,2023"));
        $(".table-responsive").shouldHave(text("Maths, Computer Science, Economics, Physics"));

        //Hobbies
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("Music"));


        $(".table-responsive").shouldHave(text("вторичка.jpeg"));
        $(".table-responsive").shouldHave(text("TestAddress"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
    }
}

