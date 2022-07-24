import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.Duration.ofSeconds;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        Optional<Path> browserPath = ChromeDriverManager.chromedriver().getBrowserPath();
        assertThat(browserPath)
                .as("Chrome browser should be installed to run the tests")
                .isPresent();

        ChromeDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(ofSeconds(100));
        driver.manage().timeouts().implicitlyWait(ofSeconds(100));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public void takeScreenshotOnFailure(String testMethodName) {

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        LocalDateTime date = LocalDateTime.now();
        String TimeStamp = date.format(ofPattern("yyyy_MMMM_dd_HH_mm_ss"));

        Path screenshotPath = null;
        try {
            Path screenshotsDir = Paths.get("target", "surefire-reports", "failure_screenshots");
            if (Files.notExists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }

            screenshotPath = screenshotsDir.resolve(testMethodName + "_" + TimeStamp + ".png");
            Files.write(screenshotPath, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (screenshotPath != null) {
            Reporter.log("<a href='" + screenshotPath.toAbsolutePath() + "'> <img src='" + screenshotPath.toAbsolutePath() + "' height='100' width='100'/> </a>");
        }
    }
}
