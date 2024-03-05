package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class JUnit5HW extends TestBaseChelseaHW{

    @BeforeEach
    void setUp() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.chelseafc.com/en");

        $(".search-toggle").click();
        $("[data-testid='search__field']").shouldHave(attribute("placeholder", "What are you looking for?"));
    }

    @ValueSource(strings = {
            "Silva", "Mudryk", "Jackson"
    })
    @ParameterizedTest(name = "Карточка {0} отображается после поиска по фамилии")
    void cardOfFootballerShouldAppearAfterSearchBySurname(String footballPlayerSurname) {

        $("[data-testid='search__field']").setValue(footballPlayerSurname).pressEnter();
        $("[data-testid='search-players']").shouldHave(text(footballPlayerSurname));

    }

    @CsvSource(value = {
            "Silva , /en/teams/profile/thiago-silva",
            "Mudryk , /en/teams/profile/mykhailo-mudryk",
            "Jackson , /en/teams/profile/nicolas-jackson"
    })
    @ParameterizedTest(name = "Ссылка {1} имеется в карточке футболиста после поиска по фамилии {0}")
    void linkToFootballerProfileShouldBeWithHisCard(String footballPlayerSurname, String expectedLink) {

        $("[data-testid='search__field']").setValue(footballPlayerSurname).pressEnter();
        $("[data-testid='search-players']").shouldHave(text(footballPlayerSurname));
        $("[data-testid='search-players'] [data-testid='link']").click();
        webdriver().shouldHave(url("https://www.chelseafc.com" + expectedLink));
    }

    @CsvFileSource(resources = "/test_data/linkToFootballerProfileShouldBeWithHisCardCsv.csv")
    @ParameterizedTest(name = "Ссылка {1} имеется в карточке футболиста после поиска по фамилии {0}")
    void linkToFootballerProfileShouldBeWithHisCardCsv(String footballPlayerSurname, String expectedLink) {

        $("[data-testid='search__field']").setValue(footballPlayerSurname).pressEnter();
        $("[data-testid='search-players']").shouldHave(text(footballPlayerSurname));
        $("[data-testid='search-players'] [data-testid='link']").click();
        webdriver().shouldHave(url("https://www.chelseafc.com" + expectedLink));
    }


}