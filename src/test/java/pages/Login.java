package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.SelenideActions;

import static com.codeborne.selenide.Selenide.$;

public class Login {

    public static void inputEmail(String email) {
        SelenideElement emailField = $(By.id("email"));

        SelenideActions
                .existVisibleScrollHoverClick(
                        emailField);
        emailField.sendKeys(email);
    }

    public static void inputPassword(String password) {
        SelenideElement emailField = $(By.id("password"));

        SelenideActions
                .existVisibleScrollHoverClick(
                        emailField);
        emailField.sendKeys(password);
    }

    public static void clickLogin() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.id("logIn")));
    }

    public static void clickRememberMe() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.xpath(".//input[@id='remember-me']/following-sibling::label")));

    }

    public static void clickNeedHelp() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.id("forgot-password-link")));
    }

    public static void checkResetButton() {
        $(By.id("resetBtn")).shouldBe(Condition.visible);
    }

    public static void clickResetPassword() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.id("resetBtn")));
    }

    public static void checkReset() {
        $(By.className("reset-sent-container fade-out-right")).shouldBe(Condition.visible);
    }

    public static void clickBackToLogin() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.id("back-to-login")));
    }

    public static void checkBackToLogin() {
        $(By.id("logIn")).shouldBe(Condition.visible);
    }

    public static boolean checkDisabledLoginButton() {
        boolean disabled = false;
        try {
            disabled = $(By.id("logIn")).getAttribute("disabled").equals("disabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disabled;
    }

    public static boolean checkUnsuccessfulLogin() {
        $(By.className("login-error-container")).shouldBe(Condition.visible);
        return $(By.className("login-error-container")).is(Condition.visible) && Selenide.webdriver().driver().url().contains("/login");
    }

    public static void checkNoUserReset() {
        $(By.xpath(".//p[contains(text(), \"That email address doesn't exist in Hudl\")]")).shouldBe(Condition.visible);
    }

    public static void checkInvalidEmailReset() {
        $(By.xpath(".//p[contains(text(), \"That isn't a valid email address\")]")).shouldBe(Condition.visible);
    }

}
