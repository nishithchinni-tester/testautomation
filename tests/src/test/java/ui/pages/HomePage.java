package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;
import org.testng.Assert;

public class HomePage extends BasePage {

    public WebDriver driver;
    WebDriverUtils webDriverUtils = new WebDriverUtils();
    Logger log = LogManager.getLogger(HomePage.class);
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "left-section-waffle-menu-button")
    public WebElement menu;

    @FindBy(xpath = "//*[@data-test-id = 'trialExperienceTopRowCreateNewAppButton']")
    public WebElement newAppButton;

    @FindBy(xpath = "//*[@data-test-id = 'menu-link-apps']")
    public WebElement appsMenu;

    @FindBy(xpath = "(//td[@data-test-id = 'grid-data-cell'][2]//span)[3]")
    public WebElement app;

    @FindBy(xpath = "//*[text()='App settings']")
    public WebElement appSettings;

    public boolean isUserOnHomePage(){
        log.info("Validating User is on HomePage");
        return webDriverUtils.isElementPresent(newAppButton);
    }

    public HomePage clickOnMenu(){
        webDriverUtils.waitForElement(2000).click(menu);
        log.info("Clicked on Waffle Menu");
        return this;
    }

    public HomePage clickOnAppsMenu(){
        webDriverUtils.click(appsMenu);
        log.info("Clicked on Apps Menu");
        return this;
    }

    public HomePage clickOnApp(){
        webDriverUtils.waitForElement(5000).click(app,20);
        log.info("Clicked on Application");
        return this;
    }

    public AppSettingsPage clickOnAppSettings(){
        webDriverUtils.click(appSettings);
        log.info("Clicked on App Settings");
        return createInstance(AppSettingsPage.class,driver);
    }

    public HomePage validateAppName(String appName){
        log.info("Validating App Name");
        Assert.assertEquals(webDriverUtils.getText(app), appName);
        return this;
    }


}
