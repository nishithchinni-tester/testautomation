package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;

public class SignInPage extends BasePage {
    private final WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final WebDriverUtils driverUtils = new WebDriverUtils();
    private final Logger log = LogManager.getLogger(SignInPage.class);
    @FindBy(name = "loginid")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "signin")
    private WebElement signIn;

    public SignInPage enterUserName(String usrName) {
        driverUtils.setText(userName, usrName);
        log.info("Set Text in UserName {}", usrName);
        return this;
    }

    public SignInPage enterPassword(String pswd) {
        driverUtils.setText(password, pswd);
        log.info("Set Text in Password {}", pswd);
        return this;
    }

    public <T> T clickOnSignInButton(Class<T> pageClass) {
        driverUtils.click(signIn);
        log.info("Clicked on SignIn Button");
        return createInstance(pageClass, driver);
    }
}
