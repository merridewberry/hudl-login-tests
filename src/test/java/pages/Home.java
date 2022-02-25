package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import utils.PropertiesManager;
import utils.SelenideActions;

import static com.codeborne.selenide.Selenide.$;

public class Home {

    public static boolean checkSuccessfulLogin() {
        SelenideActions
                .existVisibleScrollHover($(By.xpath(".//div[@class='hui-globaluseritem__display-name']/span")));
        $(By.xpath(".//div[@class='hui-globaluseritem__email']")).shouldBe(Condition.visible);

        return $(By.xpath(".//div[@class='hui-globaluseritem__email']")).getText().equals(PropertiesManager.getProperty("email"));
    }
}
