package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseChelseaHW {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.chelseafc.com/en");
        $("#onetrust-accept-btn-handler").click();
    }

}
