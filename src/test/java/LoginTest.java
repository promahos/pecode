import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;
import pageobjects.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestStatusListener.class)
public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private LoginHelper loginHelper;

    @BeforeMethod
    public void openPage() {
        driver.get("https://www.pecodesoftware.com/qa-portal/greet.php");

        loginPage = new LoginPage(driver);
        loginHelper = new LoginHelper(loginPage);

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();
    }
    
    @Test
    public void testElementsArePresent() {
        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(loginPage.isLoginFieldDisplayed())
                .as("Login field should be displayed")
                .isTrue();
        soft.assertThat(loginPage.isPasswordFieldDisplayed())
                .as("Password field should be displayed")
                .isTrue();
        soft.assertThat(loginPage.isSubmitButtonDisplayed())
                .as("Submit button should be displayed")
                .isTrue();

        soft.assertAll();
    }

    @Test()
    public void testValidLoginValidPassword() {
        loginHelper.signIn(DataProvider.VALID_LOGIN, DataProvider.VALID_PASSWORD);

        assertThat(loginPage.isOpen())
                .as("Login page should not be open")
                .isFalse();
    }
    
    @Test
    public void testEmptyLoginEmptyPassword() {
        loginHelper.signIn("", "");

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();

        String expectedLoginMessage = "Please enter username.";
        String expectedPasswordMessage = "Please enter your password.";

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(loginPage.getLoginErrorMessage())
                .as("Login error message should appear")
                .isEqualTo(expectedLoginMessage);
        soft.assertThat(loginPage.getPasswordErrorMessage())
                .as("Password error message should appear")
                .isEqualTo(expectedPasswordMessage);

        soft.assertAll();
    }

    @Test
    public void testValidLoginEmptyPassword() {
        loginHelper.signIn(DataProvider.VALID_LOGIN, "");

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();

        String expectedPasswordMessage = "Please enter your password.";

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(loginPage.getLoginErrorMessage())
                .as("Login error message should not appear")
                .isBlank();
        soft.assertThat(loginPage.getPasswordErrorMessage())
                .as("Password error message should appear")
                .isEqualTo(expectedPasswordMessage);

        soft.assertAll();
    }

    @Test
    public void testEmptyLoginValidPassword() {
        loginHelper.signIn("", DataProvider.VALID_PASSWORD);

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();

        String expectedLoginMessage = "Please enter username.";

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(loginPage.getLoginErrorMessage())
                .as("Login error message should appear")
                .isEqualTo(expectedLoginMessage);
        soft.assertThat(loginPage.getPasswordErrorMessage())
                .as("Password error message should not appear")
                .isBlank();

        soft.assertAll();
    }

    @Test
    public void testInvalidLogin() {
        loginHelper.signIn("invalid", DataProvider.VALID_PASSWORD); // password shouldn't be empty

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();

        String expectedLoginMessage = "No account found with that username.";

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(loginPage.getLoginErrorMessage())
                .as("Login error message should appear")
                .isEqualTo(expectedLoginMessage);
        soft.assertThat(loginPage.getPasswordErrorMessage())
                .as("Password error message should not appear")
                .isBlank();

        soft.assertAll();
    }

    @Test
    public void testValidLoginInvalidPassword() {
        loginHelper.signIn(DataProvider.VALID_LOGIN, "password");

        assertThat(loginPage.isOpen())
                .as("Login page should be open")
                .isTrue();

        String expectedPasswordMessage = "The password you entered was not valid.";

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(loginPage.getLoginErrorMessage())
                .as("Login error message should not appear")
                .isBlank();
        soft.assertThat(loginPage.getPasswordErrorMessage())
                .as("Password error message should appear")
                .isEqualTo(expectedPasswordMessage);

        soft.assertAll();
    }
}
