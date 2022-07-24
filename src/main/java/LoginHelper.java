import pageobjects.LoginPage;

public class LoginHelper {

    private final LoginPage loginPage;

    public LoginHelper(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void signIn(String login, String password) {
        loginPage.enterLogin(login)
                .enterPassword(password)
                .clickSignUp();
    }
}
