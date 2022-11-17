import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.reportsFolder;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

@Listeners({SoftAsserts.class})
public class SelenideTests {
    public SelenideTests(){
        Configuration.timeout=5000;
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";
        baseUrl="https://the-internet.herokuapp.com";
        reportsFolder="src/main/resources/Failed";
    }

    @Test
    public void dropdownTest(){
    open("/dropdown");
    $("#dropdown").selectOptionContainingText("tion 2");
    $("#dropdown").getSelectedOption().shouldHave(exactText("Option 2"));
    }

    @Test
    public void inputTest(){
        open("/inputs");
        $("input[type=number]").setValue("100");
        $("input[type=number]").shouldBe(empty);
    }

    @Test
    public void textBoxTest(){
        open("https://demoqa.com/text-box ");
        $("#userName").setValue("Nika Palagashvili");
        $("input[type=email]").setValue("nikapalagashvili@gmail.com");
        $(by("placeholder","Current Address")).setValue("Tbilisi, Georgia, Meliqishvili Avenue");
        $(".form-control", 3).setValue("Tbilisi, Georgia, Chavchavadze Avenue");
        $("#submit").click();
        $$("#output").shouldHave(exactTexts("Name:Nika Palagashvili\n" + "Email:nikapalagashvili@gmail.com\n" + "Current Address :Tbilisi, Georgia, Meliqishvili Avenue\n" + "Permananet Address :Tbilisi, Georgia, Chavchavadze Avenue"));
    }
}
