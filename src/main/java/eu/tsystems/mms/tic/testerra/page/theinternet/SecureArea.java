package eu.tsystems.mms.tic.testerra.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureArea extends Page {
    private GuiElement successMessage = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='flash' and @class='flash success']"));
    private GuiElement closeMessageIcon = successMessage.getSubElement(By.className("close"));

    private GuiElement header = new GuiElement(this.getWebDriver(), By.tagName("h2"));

    private GuiElement subHeader = new GuiElement(this.getWebDriver(), By.className("subheader"));

    private GuiElement logoutButton = new GuiElement(this.getWebDriver(), By.linkText("Logout"));

    public SecureArea(WebDriver driver) {
        super(driver);
    }

    public String getSecureAreaUrl() {
        return this.getWebDriver().getCurrentUrl();
    }

    public String getPageHeader() {
        return this.header.getText();
    }

    public boolean verifyPageSubheader() {
        return this.subHeader.getText().contains("Welcome to the Secure Area");
    }


    public String getSuccessMessage () {
        boolean success = this.successMessage.waits().waitForIsDisplayed();
        if (success) {
            String successMessageTextAll = this.successMessage.getText();
            String closeIconText = this.closeMessageIcon.getText();
            String successMessageText = successMessageTextAll.replace(closeIconText, "").trim();
            return successMessageText;
        }
        return "Login success flash message not displayed";
    }

    public FormAuthenticationPage logoutSuccessful() {
        this.logoutButton.click();
        return PageFactory.create(FormAuthenticationPage.class, this.getWebDriver());
    }

}
