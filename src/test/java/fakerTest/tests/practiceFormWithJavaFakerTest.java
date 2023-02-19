package fakerTest.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class practiceFormWithJavaFakerTest {





    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

    }
    @Test
    void fillPracticeForm(){
//        Faker faker = new Faker();
        Faker faker = new Faker(new Locale("it"));

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String address = faker.address().streetAddress();

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()"); // delete reklama
        executeJavaScript("$('footer').remove()"); // delete footer

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));


        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
//        $("#gender-radio-1").click(); wrong way ne ckicknet
//        $("#gender-radio-1").parent().click(); // good way
        $("[for=gender-radio-1]").click(); // good way
        $("#genterWrapper").$(byText("Male")).click(); // po yazykam ne proidet good way
        $("#userNumber").setValue("8777777777");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
//        $(".react-datepicker__month-select").selectOptionByValue("6"); same as july

        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Math").pressEnter();

        $("#hobbiesWrapper").$(byText("Reading")).click(); // po yazykam ne proidet good way

        $("#uploadPicture").uploadFromClasspath("img/img/1.png");// esli fail v resurce
        //        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
//        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName),text(lastName),
                text(userEmail),text("8777777777"),text(address)); // i togdalee






        ;



    }
}


