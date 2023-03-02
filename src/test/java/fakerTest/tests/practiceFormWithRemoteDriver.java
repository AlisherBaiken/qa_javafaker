package fakerTest.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class practiceFormWithRemoteDriver extends TestBaseExtended{


String userName = "Alex",
     userLastName = "Baikenov",
     userEmail = "123@mail.ru",
     userGender = "Male",
     userPhone = "8777777777",
     userSubject ="Math";
    @Test
    @Tag("remote")
    void fillPracticeForm(){
        String userName = "Alex";
        step("Open form",()->{
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()"); // delete reklama
            executeJavaScript("$('footer').remove()"); // delete footer
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill form",()->{
        $("#firstName").setValue(userName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);
        $("[for=gender-radio-1]").click(); // good way
        $("#genterWrapper").$(byText(userGender)).click(); // po yazykam ne proidet good way
        $("#userNumber").setValue(userPhone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue(userSubject).pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click(); // po yazykam ne proidet good way
        $("#uploadPicture").uploadFromClasspath("img/img/1.png");// esli fail v resurce
        $("#currentAddress").setValue("Alatau");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();
        });

        step("Verify results",()->{
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName),text("Baikenov"),
                text("123@mail.ru"),text("8777777777")); // i togdalee
        });





        ;



    }
}
