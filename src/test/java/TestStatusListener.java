import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestStatusListener extends TestBase implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshotOnFailure(result.getName());
    }
}
