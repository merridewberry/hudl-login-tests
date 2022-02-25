package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.lang.invoke.MethodHandles;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps", "utils"},
        tags = "@test",
        plugin = {"pretty"}
)
public class RunnerTest extends AbstractTestNGCucumberTests {

    private static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());

    Scenario scenario;

    @Before
    public static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @Before
    public void open() {
        Selenide.open(PropertiesManager.getProperty("url"));
    }

    @After
    public void logResult(Scenario scenario) {
        this.scenario = scenario;
        log.debug(scenario.getName() + "----" + scenario.getStatus());
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
