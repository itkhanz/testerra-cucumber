package eu.tsystems.mms.tic.testerra.test.cucumber.steps;

import eu.tsystems.mms.tic.testerra.page.theinternet.FormAuthenticationPage;
import eu.tsystems.mms.tic.testerra.page.theinternet.SecureArea;
import eu.tsystems.mms.tic.testerra.page.theinternet.StartPage;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import eu.tsystems.mms.tic.testframework.report.model.steps.TestStep;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FormAuthentication_Steps {
    final WebDriver driver = WebDriverManager.getWebDriver();
    StartPage startPage = PageFactory.create(StartPage.class, driver);
    public FormAuthenticationPage formAuthenticationPage;
    public SecureArea secureArea;

    @Given("user is on Login Page")
    public void gotoLoginPage() {
        TestStep.begin("Navigating to login page");
        formAuthenticationPage = startPage.goToLoginPage();
    }

    @When("user enters the {string} in username field")
    public void enterUsername(String username){
        formAuthenticationPage.enterUsername(username);
    }

    @And("user enters the {string} in password field")
    public void enterPassword(String pwd){
        formAuthenticationPage.enterPassword(pwd);
    }

    @And("user clicks on Login button")
    public void clickLoginButton(){
        secureArea = formAuthenticationPage.clickLoginButton();
    }

    @Then("user is entered into Secure Area")
    public void verifySecureArea() {
        Assert.assertEquals(secureArea.getSecureAreaUrl(), "https://the-internet.herokuapp.com/secure");
        Assert.assertEquals(secureArea.getPageHeader(), "Secure Area");
        Assert.assertTrue(secureArea.verifyPageSubheader(), "subheader text does not contain Secure Area");
    }

    @And("user gets the message {string}")
    public void verifyLoginSuccessMessage(String loginSuccessMsg) {
        Assert.assertEquals(secureArea.getSuccessMessage(), loginSuccessMsg);
    }


}
