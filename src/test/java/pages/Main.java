package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import utils.SelenideActions;

import static com.codeborne.selenide.Selenide.$;

public class Main {

public static boolean clickLogin() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.linkText("Log in")));
        return Selenide.webdriver().driver().url().contains("/login");
    }
}
