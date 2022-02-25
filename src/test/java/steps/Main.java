package steps;

import io.cucumber.java.en.When;
import org.testng.Assert;

public class Main {

    @When("I click 'Log in' button on the main page")
    public static void clickLogin() {
        Assert.assertTrue(
                pages.Main.clickLogin());
    }

}
