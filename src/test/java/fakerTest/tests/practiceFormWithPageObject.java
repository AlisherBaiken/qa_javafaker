package fakerTest.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.registrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class practiceFormWithPageObject extends TestBase {

//    String userName = "Alex";
//    String userLastName = "Baikenov";
//    String userEmail = "123@mail.ru";
//    String userGender = "Male";
//    String userPhone = "8777777777";
//    String userSubject ="Math";


String userName = "Alex",
     userLastName = "Baikenov",
     userEmail = "123@mail.ru",
     userGender = "Male",
     userPhone = "8777777777",
     userSubject ="Math";
//static String userName = "Alex",
//         userLastName = "Baikenov",
//     userEmail = "123@mail.ru",
//     userGender = "Male",
//     userPhone = "8777777777",
//     userSubject ="Math";
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
//        userName = "Alex";
//                userLastName = "Baikenov";
//                userEmail = "123@mail.ru"; /// esli ne budu menyatsya dlya vseh testsov
//                userGender = "Male";
//                userPhone = "8777777777";
//                userSubject ="Math";

    }
    @BeforeEach
    static void beforeEach() {

//        userName = getNewUserName();
//        userLastName = getNewLastUserName();
//        userEmail = getUserEmail();
//        userGender = getUserGender();
//        userPhone = getUserPhone();
//        userSubject =getUserSubject();

    }
    @Test
    void fillPracticeForm() {
        String userName = "Alex";
        RegistrationPage.openPage();
        RegistrationPage.setFirstName(userName);
        RegistrationPage.setLastName("Baikenov");
        RegistrationPage.setEmail("123@mail.ru");
        RegistrationPage.setGender("Male");
        RegistrationPage.setPhone("8777777777");

        // dalee takje obrabotat
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click(); // po yazykam ne proidet good way
        $("#uploadPicture").uploadFromClasspath("img/1.png");// esli fail v resurce
        $("#currentAddress").setValue("Alatau");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName), text("Baikenov"),
                text("123@mail.ru"), text("8777777777")); // i togdalee
    }

    @Test
    void fillPractice2Form() {
//        String userName = "Alex";
//        String userLastName = "Baikenov";
//        String userEmail = "123@mail.ru";
//        String userGender = "Male";
//        String userPhone = "8777777777";
//        String userSubject ="Math";
        RegistrationPage.openPage()
                .setFirstName(userName)
                .setLastName(userLastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setPhone(userPhone)
                .setBirthday("30","July","2008")
                .setSubject(userSubject)
                .setHobbies("Reading")
                .uploadPicturue("img/1.png")
                .setCurrentAddress("Alatau")
                .setState("NCR")
                .setCity("Noida")
                .clickSumbit();
        RegistrationPage.verifyResultModalAppear()
                .verifyResult("Student Name", userName + userLastName)
                .verifyResult("Student Email",userEmail) // mojem proverit vse
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userPhone)
                .verifyResult("Date of Birth", "30 July,2008");

    }
}
