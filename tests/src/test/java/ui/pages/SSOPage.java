package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;

public class SSOPage extends BasePage{

    WebDriver driver;
    public SSOPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    WebDriverUtils driverUtils = new WebDriverUtils();
    Logger log = LogManager.getLogger(SSOPage.class);

    @FindBy(id = "quickbaseSignin")
    public WebElement no;

    @FindBy(xpath = "//*[text()='Do you have a Quickbase single sign-on (SSO) account?']")
    public WebElement ssoTextHeader;

    public SignInPage clickOnNOButton(){
        driverUtils.click(no);
        log.info("Clicked on NO Button");
        return createInstance(SignInPage.class, driver);
    }
    public boolean isSSOTextHeaderVisible(){
        log.info("Validating SSO Text Header is Visible");
        return driverUtils.isElementPresent(ssoTextHeader);
    }
}
