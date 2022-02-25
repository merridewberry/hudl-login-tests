package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import utils.PropertiesManager;

import java.lang.invoke.MethodHandles;

public class Login {
    private static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @And("I input valid email")
    public static void inputValidEmail() {
        pages.Login.inputEmail(PropertiesManager.getProperty("email"));
    }

    @And("I input valid password")
    public static void inputValidPassword() {
        pages.Login.inputPassword(PropertiesManager.getProperty("password"));
    }

    @And("I click 'Log in' button on the login page")
    public static void clickLogin() {
        pages.Login.clickLogin();
    }

    @Then("I'm logged in")
    public static void checkSuccessfulLogin() {
        Assert.assertTrue(
                pages.Home.checkSuccessfulLogin());
    }

    @And("^I input the following email (.+)$")
    public static void inputInvalidEmail(String email) {
        pages.Login.inputEmail(email);
    }

    @And("^I input the following password (.+)$")
    public static void inputInvalidPassword(String password) {
        pages.Login.inputPassword(password);
    }

    @Then("I get an error message")
    public static void checkUnsuccessfulLogin() {
        Assert.assertTrue(
                pages.Login.checkUnsuccessfulLogin());
    }

    @When("I click on 'Need help' button")
    public static void clickNeedHelp() {
        pages.Login.clickNeedHelp();
    }

    @Then("I see password reset form")
    public static void clickNeedHelpCheck() {
        pages.Login.checkResetButton();
    }

    @And("I click 'Send Password Reset' button")
    public static void clickReset() {
        pages.Login.clickResetPassword();
    }

    @Then("I see the message asking me to check my e-mail")
    public static void checkReset() {
        pages.Login.checkReset();
    }

    @And("I click on 'Back' button")
    public static void clickBackToLogin() {
        pages.Login.clickBackToLogin();
    }

    @Then("I return to login screen")
    public static void checkBackToLogin() {
        pages.Login.checkBackToLogin();
    }

    @And("I tick 'Remember me' checkbox")
    public static void clickRememberMe() {
        pages.Login.clickRememberMe();
    }

    @And("^I receive cookie (.+) expiry date$")
    public static void restart(String type) {
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("ident");
        if (cookie.getExpiry() == null) {
            Assert.assertEquals(type, "without");
        } else {
            Assert.assertEquals(type, "with");
        }
    }

    @And("^I input valid password (.+)$")
    public static void changePasswordTail(String tail) {
        String password = PropertiesManager.getProperty("password");
        if (tail.equals("with extra symbol")) {
            pages.Login.inputPassword(password + "x");
        }
        if (tail.equals("without last symbol")) {
            pages.Login.inputPassword(StringUtils.chop(password));
        }

    }

    @And("^I input valid email with extra spaces in the (.+)$")
    public static void emailWithSpaces(String placement) {
        String email = PropertiesManager.getProperty("email");

        if (placement.equals("end")) {
            pages.Login.inputEmail(email + "  ");
        }
        if ((placement.equals("beginning"))) {
            pages.Login.inputEmail("  " + email);
        }
    }

    @And("^I input valid password with extra spaces in the (.+)$")
    public static void passwordWithSpaces(String placement) {
        String password = PropertiesManager.getProperty("password");

        if (placement.equals("end")) {
            pages.Login.inputEmail(password + "  ");
        }
        if ((placement.equals("beginning"))) {
            pages.Login.inputEmail("  " + password);
        }
    }

    @And("^I input valid password, but changed to (.+) case$")
    public static void passwordChangeCase(String type) {
        String password = PropertiesManager.getProperty("password");

        if (type.equals("upper")) {
            pages.Login.inputPassword(password.toUpperCase());
        }
        if (type.equals("lower")) {
            pages.Login.inputPassword(password.toLowerCase());
        }
    }

    @And("I input valid email, but changed to upper case")
    public static void emailUpperCase() {
        String email = PropertiesManager.getProperty("email");

        pages.Login.inputEmail(email.toUpperCase());
    }

    @And("I get an error message saying there's no such user")
    public static void checkNoUserReset() {
        pages.Login.checkNoUserReset();
    }

    @And("I get an error message saying that isn't a valid email")
    public static void checkInvalidEmailReset() {
        pages.Login.checkInvalidEmailReset();
    }

}
