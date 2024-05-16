package ru.netology.testmode;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.DataGenerator.Registration.getRegisterUser;
import static ru.netology.testmode.DataGenerator.Registration.getUser;
import static ru.netology.testmode.DataGenerator.getRandomLogin;
import static ru.netology.testmode.DataGenerator.getRandomPassword;

public class TestModeTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("registration user positive test")
    void registrationUserPositiveTest() {
        var registeredUser = getRegisterUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("h2")
                .shouldHave(text("Личный кабинет"))
                .shouldBe(Condition.visible);
    }

//    @Test
//    @DisplayName("not register user error test")
//    void notRegisterUserErrorTest() {
//        var notRegisteredUser = getRegisterUser("active");
//        $("[data-test-id='login'] input").setValue(notRegisteredUser.getLogin());
//        $("[data-test-id='password'] input").setValue(notRegisteredUser.getPassword());
//        $("button.button").click();
//        $("[data-test-id='error-notification'] .notification__content")
//                .shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(10))
//                .shouldBe((Condition.visible));
//    }

    @Test
    @DisplayName("user blocked error test")
    void userBlockedErrorTest() {
        var blockedUser = getRegisterUser("blocked");
        $("[data-test-id='login'] input").setValue(blockedUser.getLogin());
        $("[data-test-id='password'] input").setValue(blockedUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(text("Ошибка! Пользователь заблокирован"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));
    }

    @Test
    @DisplayName("registration user random login")
    void registrationUserRandomLogin() {
        var registeredUser = getRegisterUser("active");
//        var wrongLogin = getRandomLogin();
        $("[data-test-id='login'] input").setValue(getRandomLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));
    }

    @Test
    @DisplayName("registration user random password")
    void registrationUserRandomPassword() {
        var registeredUser = getRegisterUser("active");
//        var wrongPassword = getRandomPassword();
        $("[data-test-id='login'] input").setValue(getRandomPassword());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(10))
                .shouldBe((Condition.visible));
    }


}
