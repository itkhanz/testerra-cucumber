package eu.tsystems.mms.tic.testerra.page.theinternet;

import eu.tsystems.mms.tic.testerra.page.theinternet.partials.FooterPartialPage;
import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthenticationPage extends Page {
    private FooterPartialPage footer = PageFactory.create(FooterPartialPage.class, this.getWebDriver());

    protected String validUsername = "tomsmith";
    protected String validPassword = "SuperSecretPassword!";
    protected String invalidUsername = "test";
    protected String invalidPassword = "1234";

    private GuiElement header = new GuiElement(this.getWebDriver(), By.tagName("h2"));
    @Check
    private GuiElement usernameField = new GuiElement(this.getWebDriver(), By.id("username"));

    @Check
    private GuiElement passwordField = new GuiElement(this.getWebDriver(), By.id("password"));

    @Check
    private GuiElement loginButton = new GuiElement(this.getWebDriver(), By.className("radius"));

    private GuiElement errorMessage = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='flash' and @class='flash error']"));

    private GuiElement successMessage = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='flash' and @class='flash success']"));

    private GuiElement closeMessageIcon = errorMessage.getSubElement(By.className("close"));

    public FormAuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginPageUrl() {
        return this.getWebDriver().getCurrentUrl();
    }

    public String getPageHeader() {
        return this.header.getText();
    }

    public String getSuccessMessage () {
        return this.successMessage.getText();
    }
    public String getErrorMessage () {
        boolean success = this.errorMessage.waits().waitForIsDisplayed();
        if (success) {
            String errorMessageTextAll = this.errorMessage.getText();
            String closeIconText = this.closeMessageIcon.getText();
            String errorMessageText = errorMessageTextAll.replace(closeIconText, "").trim();
            return errorMessageText;
        }
        return "Login failed flash message not displayed";
    }

    public SecureArea loginSuccessful() {
        this.usernameField.type(this.validUsername);
        this.passwordField.type(this.validPassword);
        this.loginButton.click();

        return PageFactory.create(SecureArea.class, this.getWebDriver());
    }

    public void loginFailed() {
        this.usernameField.type(this.invalidUsername);
        this.passwordField.type(this.invalidPassword);
        this.loginButton.click();
    }

    public void enterUsername(String name) {
        this.usernameField.type(name);
    }
    public void enterPassword(String pwd){
        this.passwordField.type(pwd);
    }
    public SecureArea clickLoginButton(){
        this.loginButton.click();
        return PageFactory.create(SecureArea.class, this.getWebDriver());
    }



}
