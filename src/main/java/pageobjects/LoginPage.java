package pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    WebElement loginField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//h1[contains(text(),'AQA internship Login')]")
    WebElement loginPageTitle;
    @FindBy(xpath = "//input[@name='username']/following-sibling::span")
    WebElement loginErrorMessage;
    @FindBy(xpath = "//input[@name='password']/following-sibling::span")
    WebElement passwordErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSignUp() {
        submitButton.click();
    }

    public boolean isOpen() {
        return loginPageTitle.isDisplayed();
    }

    public boolean isLoginFieldDisplayed() {
        try {
            return loginField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPasswordFieldDisplayed() {
        try {
            return passwordField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSubmitButtonDisplayed() {
        try {
            return submitButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }
}
